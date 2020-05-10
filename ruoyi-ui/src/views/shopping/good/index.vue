<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="商品类型">
        <el-select v-model="queryParams.goodTypeId" placeholder="商品类型" clearable size="small">
          <el-option
            v-for="goodType in goodTypeOptions"
            :key="goodType.goodTypeId"
            :label="goodType.goodTypeName"
            :value="goodType.goodTypeId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="商品名称" prop="goodName">
        <el-input
          v-model="queryParams.goodName"
          placeholder="请输入商品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:good:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:good:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:good:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:good:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="goodList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品编号" align="center" prop="goodId" />
      <el-table-column label="商品名称" align="center" prop="goodName" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="销售量" align="center" prop="sales" />
      <el-table-column label="概述" align="center" prop="summary" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:good:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:good:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改商品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="780px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="商品类型" prop="goodTypeId">
              <treeselect v-model="form.goodTypeId" :options="goodTypeTree" :normalizer="normalizer" placeholder="选择所属商品类型" :disable-branch-nodes="true"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="商品名称" prop="goodName">
              <el-input v-model="form.goodName" placeholder="请输入商品名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="价格" prop="price">
              <el-input v-model="form.price" placeholder="请输入价格" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="销售量" prop="sales">
              <el-input v-model="form.sales" placeholder="请输入销售量" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="上传图片" prop="pic">
          <el-upload action="#" :http-request="requestUpload" :show-file-list="false" :before-upload="beforeUpload">
            <el-button size="small">
              上传
              <i class="el-icon-upload el-icon--right"></i>
            </el-button>
          </el-upload>
          <img v-bind:src="options.img" class="img-lg" />
          <vue-cropper
            ref="cropper"
            :img="options.img"
            :info="true"
            :autoCrop="options.autoCrop"
            :autoCropWidth="options.autoCropWidth"
            :autoCropHeight="options.autoCropHeight"
            :fixedBox="options.fixedBox"
            @realTime="realTime"
          />
        </el-form-item>
        <el-form-item label="概述" prop="summary">
          <!--<el-input v-model="form.summary" type="textarea" placeholder="请输入概述内容" />-->
          <Editor v-model="form.summary" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listGood, getGood, delGood, addGood, updateGood, exportGood, listGoodTypeOptions, uploadImage } from "@/api/shopping/good";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import store from "@/store";
import { VueCropper } from "vue-cropper";
import Editor from '@/components/Editor';

export default {
  name: "Good",
  components: { Treeselect, VueCropper, Editor},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 商品表格数据
      goodList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodName: undefined,
        price: undefined,
        sales: undefined,
        summary: undefined,
        pic: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      goodTypeOptions: [],
      goodTypeTree: [],
      options: {
          img: store.getters.avatar, //裁剪图片的地址
          autoCropWidth: 200, // 默认生成截图框宽度
          autoCropHeight: 200, // 默认生成截图框高度
      },
    };
  },
  created() {
    this.getList();
    this.getGoodTypeOptions();
  },
  methods: {
    /** 查询商品列表 */
    getList() {
      this.loading = true;
      listGood(this.queryParams).then(response => {
        this.goodList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getGoodTypeOptions(){
        listGoodTypeOptions().then(response => {
            this.goodTypeOptions = response.rows;
        });
    },
    /** 转换商品类型数据结构 */
    normalizer(node) {
        if (node.children && !node.children.length) {
            delete node.children;
        }
        return {
            id: node.goodTypeId,
            label: node.goodTypeName,
            children: node.children
        };
    },
    /** 查询商品类型下拉树结构 */
    getTreeselect() {
        listGoodTypeOptions().then(response => {
            this.goodTypeTree = this.handleTree(response.data, "goodTypeId");
        });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        goodId: undefined,
        goodName: undefined,
        price: undefined,
        sales: undefined,
        summary: undefined,
        pic: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.goodId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.getTreeselect();
      this.reset();
      this.open = true;
      this.title = "添加商品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.getTreeselect();
      this.reset();
      const goodId = row.goodId || this.ids
      getGood(goodId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品";
        this.options.img = '/dev-api'+this.form.pic;
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.goodId != undefined) {
            updateGood(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addGood(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const goodIds = row.goodId || this.ids;
      this.$confirm('是否确认删除商品编号为"' + goodIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delGood(goodIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有商品数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportGood(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
    // 覆盖默认的上传行为
    requestUpload() {
    },
    // 上传预处理
    beforeUpload(file) {
        if (file.type.indexOf("image/") == -1) {
            this.msgError("文件格式错误，请上传图片类型,如：JPG，PNG后缀的文件。");
        } else {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
                this.options.img = reader.result;
                this.$refs.cropper.getCropBlob(data => {
                    this.form.image = data;
                    this.uploadImg();
                });
            };
        }
    },
    // 上传图片
    uploadImg() {
        this.$refs.cropper.getCropBlob(data => {
            let formData = new FormData();
            formData.append("image", data);
            uploadImage(formData).then(response => {
                if (response.code === 200) {
                    this.options.img = process.env.VUE_APP_BASE_API + response.imgUrl;
                    this.form.pic = response.imgUrl;
                } else {
                    this.msgError(response.msg);
                }
                this.$refs.cropper.clearCrop();
            });
        });
    },
    // 实时预览
    realTime(data) {
        this.previews = data;
    }
  }
};
</script>
