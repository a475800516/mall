<template>
  <div>
    <headerBar :headerBar="headerBar"/>

    <div class="content content-nav-base commodity-content">
      <div class="main-nav">
        <div class="inner-cont0">
          <div class="inner-cont1 w1200">
            <div class="inner-cont2">
              <a href="commodity.html" class="active">所有商品</a>
              <a href="buytoday.html">今日团购</a>
              <a href="information.html">母婴资讯</a>
              <a href="about.html">关于我们</a>
            </div>
          </div>
        </div>
      </div>
      <div class="commod-cont-wrap">
        <div class="commod-cont w1200 layui-clear">
          <div class="left-nav">
            <div class="title">所有分类</div>
              <div class="head-container">
                <el-tree
                  :data="goodTypeOptions"
                  :props="defaultProps"
                  :expand-on-click-node="false"
                  :filter-node-method="filterNode"
                  ref="tree"
                  default-expand-all
                  @node-click="handleNodeClick"
                />
              </div>
            <!--<div class="list-box">
              <dl>
                <dt @click="dlClick($event)" :class="isActive ? 'active' : ''" >奶粉辅食</dt>
                <dd><a href="javascript:;">进口奶粉</a></dd>
                <dd><a href="javascript:;">宝宝辅食</a></dd>
                <dd><a href="javascript:;">营养品</a></dd>
              </dl>
              <dl>
                <dt>儿童用品</dt>
                <dd><a href="javascript:;">纸尿裤</a></dd>
                <dd><a href="javascript:;">婴儿湿巾</a></dd>
                <dd><a href="javascript:;">婴儿车</a></dd>
                <dd><a href="javascript:;">婴儿床</a></dd>
                <dd><a href="javascript:;">儿童安全座椅</a></dd>
              </dl>
            </div>-->
          </div>
          <div class="right-cont-wrap">
            <div class="right-cont">
              <div class="sort layui-clear">
                <a class="active" href="javascript:;" event = 'volume'>销量</a>
                <a href="javascript:;" event = 'price'>价格</a>
                <a href="javascript:;" event = 'newprod'>新品</a>
                <a href="javascript:;" event = 'collection'>收藏</a>
              </div>
              <div class="prod-number">
                <span>200个</span>
              </div>
              <div class="cont-list layui-clear" id="list-cont">
                <div class="item" v-for="good in goodList">
                  <div class="img">
                    <a :href="'http://localhost/detail/' + good.goodId" target="_blank"><img style="height:281px;width:100%;" :src="'/dev-api'+good.pic"></a>
                  </div>
                  <div class="text">
                    <p class="title">{{good.goodName}}</p>
                    <p class="price">
                      <span class="pri">￥{{good.price}}</span>
                      <span class="nub">{{good.sales}}付款</span>
                    </p>
                  </div>
                </div>
                <!--<div class="item">
                  <div class="img">
                    <a href="javascript:;"><img src="../../../assets/styles/res/static/img/paging_img1.jpg"></a>
                  </div>
                  <div class="text">
                    <p class="title">森系小清新四件套</p>
                    <p class="price">
                      <span class="pri">￥200</span>
                      <span class="nub">1266付款</span>
                    </p>
                  </div>
                </div>
                <div class="item">
                  <div class="img">
                    <a href="javascript:;"><img src="../../../assets/styles/res/static/img/paging_img2.jpg"></a>
                  </div>
                  <div class="text">
                    <p class="title">森系小清新四件套</p>
                    <p class="price">
                      <span class="pri">￥200</span>
                      <span class="nub">1266付款</span>
                    </p>
                  </div>
                </div>
                <div class="item">
                  <div class="img">
                    <a href="javascript:;"><img src="../../../assets/styles/res/static/img/paging_img3.jpg"></a>
                  </div>
                  <div class="text">
                    <p class="title">森系小清新四件套</p>
                    <p class="price">
                      <span class="pri">￥200</span>
                      <span class="nub">1266付款</span>
                    </p>
                  </div>
                </div>-->
              </div>
              <div id="demo0" style="text-align: center;"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
  @import "../../../assets/styles/res/static/css/main.css";
  @import "../../../assets/styles/res/layui/css/layui.css";
  @import "../../../assets/styles/res/layui/layui.js";
</style>
<script>
import Cookies from "js-cookie";
import { listGoodType, listGood} from "@/api/shopping/mall";
import headerBar from "./headerBar";

export default {
  name: "Index",
  components: { headerBar },
  props: {
      headerBar: {
          type: Object
      }
  },
  data() {
    return {
      isActive: true,
      loading: false,
      redirect: undefined,
      defaultProps: {
          children: "children",
          label: "label"
      },
      goodTypeOptions: [/*{
          id:100,
          label:"若依科技",
          children:[{
              id:101,
              label:"深圳总公司"
          },{
              id:102,
              label:"深圳总公司"
          },{
              id:103,
              label:"深圳总公司"
          }]
      },{
          id:104,
          label:"若依科技",
          children:[{
              id:105,
              label:"深圳总公司"
          },{
              id:106,
              label:"深圳总公司"
          },{
              id:107,
              label:"深圳总公司"
          }]
      }*/],
      goodList: [/*{
          goodName: 'test1',
          price: 123,
          pic: '/dev-api/profile/avatar/2020/04/28/57f05b9f6fd3ebb8d1c609276c190de3.jpeg',
          sales: 3
      }*/]
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    // this.getCode();
    // this.getCookie();
      this.getGoodTypeList();
      this.getGoodList();
  },
  methods: {
    dlClick(event){
        console.log(event.currentTarget);
        console.log(event.currentTarget);

    },
    getGoodTypeList(){
        listGoodType().then(response => {
            this.goodTypeOptions = this.handleTree(response.data, "goodTypeId");
        });
    },
    getGoodList(){
        listGood().then(response => {
            this.goodList = response.rows;
        });
    },
    handleNodeClick(data) {
        console.log(data);
    },
    // 筛选节点
    filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
    },
    getCode() {
      getCodeImg().then(res => {
        this.codeUrl = "data:image/gif;base64," + res.img;
        this.loginForm.uuid = res.uuid;
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
  }
};
</script>
<link rel="stylesheet/scss" href="../../../assets/styles/shopping/main.css">
<link rel="stylesheet/scss" href="../../../assets/styles/shopping/layui.css">
<script type="text/javascript" src="../../../assets/styles/res/layui/layui.js"></script>
<script>
    layui.config({
        base: '../../../assets/styles/res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
    }).use(['mm','laypage','jquery'],function(){
        var laypage = layui.laypage,$ = layui.$,
            mm = layui.mm;
        laypage.render({
            elem: 'demo0'
            ,count: 100 //数据总数
        });

        $('.sort a').on('click',function(){
            $(this).addClass('active').siblings().removeClass('active');
        })
        $('.list-box dt').on('click',function(){
            if($(this).attr('off')){
                $(this).removeClass('active').siblings('dd').show()
                $(this).attr('off','')
            }else{
                $(this).addClass('active').siblings('dd').hide()
                $(this).attr('off',true)
            }
        })
    });
</script>
