<template>
  <div>
    <headerBar :headerBar="headerBar"/>

    <div class="content content-nav-base commodity-content">
      <div class="commod-cont-wrap">

        <div  class="cart py-container">
          <div class="checkout py-container">
            <div class="checkout-tit">
              <h4 class="tit-txt">填写并核对订单信息</h4>
            </div>
            <div class="checkout-steps">
              <!--收件人信息-->
              <div class="step-tit">
                <h5>收件人信息<span><a data-toggle="modal" data-target=".edit" data-keyboard="false" class="newadd">新增收货地址</a></span></h5>
              </div>
              <div class="step-cont">
                <div class="addressInfo">
                  <ul class="addr-detail">
                    <li class="addr-item" v-for="a in addressList" :key="a.id">
                      <div>
                        <div :class="{con:true,name:true,selected:a.id === selectedAddress.id}"><a href="javascript:;" @click.stop="selectedAddress=a" >{{a.name}}<span title="点击取消选择"></span>&nbsp;</a></div>
                        <div class="con address">{{a.state + a.city + a.district }} <span>{{a.phone}}</span>
                          <span class="base" v-if="a.isDefault">默认地址</span>
                          <span class="edittext"><a data-toggle="modal" data-target=".edit" data-keyboard="false" >编辑</a>&nbsp;&nbsp;<a href="javascript:;">删除</a></span>
                        </div>
                        <div class="clearfix"></div>
                      </div>

                    </li>
                  </ul>
                  <!--添加地址-->
                  <div  tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade edit">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <button type="button" data-dismiss="modal" aria-hidden="true" class="sui-close">×</button>
                          <h4 id="myModalLabel" class="modal-title">添加收货地址</h4>
                        </div>
                        <div class="modal-body">
                          <form action="" class="sui-form form-horizontal">
                            <div class="control-group">
                              <label class="control-label">收货人：</label>
                              <div class="controls">
                                <input type="text" class="input-medium">
                              </div>
                            </div>

                            <div class="control-group">
                              <label class="control-label">详细地址：</label>
                              <div class="controls">
                                <input type="text" class="input-large">
                              </div>
                            </div>
                            <div class="control-group">
                              <label class="control-label">联系电话：</label>
                              <div class="controls">
                                <input type="text" class="input-medium">
                              </div>
                            </div>
                            <div class="control-group">
                              <label class="control-label">邮箱：</label>
                              <div class="controls">
                                <input type="text" class="input-medium">
                              </div>
                            </div>
                            <div class="control-group">
                              <label class="control-label">地址别名：</label>
                              <div class="controls">
                                <input type="text" class="input-medium">
                              </div>
                              <div class="othername">
                                建议填写常用地址：<a href="#" class="sui-btn btn-default">家里</a>　<a href="#" class="sui-btn btn-default">父母家</a>　<a href="#" class="sui-btn btn-default">公司</a>
                              </div>
                            </div>
                          </form>
                        </div>
                        <div class="modal-footer">
                          <button type="button" data-ok="modal" class="sui-btn btn-primary btn-large">确定</button>
                          <button type="button" data-dismiss="modal" class="sui-btn btn-default btn-large">取消</button>
                        </div>
                      </div>
                    </div>
                  </div>
                  <!--确认地址-->
                </div>
                <div class="hr"></div>

              </div>
              <div class="hr"></div>
              <!--支付和送货-->
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

  @import "../../../assets/styles/leyou/css/webbase.css";
  @import "../../../assets/styles/leyou/css/pages-getOrderInfo.css";
</style>
<script>
import { getOrders} from "@/api/shopping/pay";
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
      addressList:[],
      selectedAddress: {},
    };
  },
  created() {
      const ordersId = this.$route.params && this.$route.params.ordersId;
      // this.getOrdersInfo(ordersId);
      this.addressList=[// 可选地址列表,假数据，需要从后台查询
          {
              id: 1,
              name:"虎哥",// 收件人姓名
              phone:"15800000000",// 电话
              state:"上海",// 省份
              city:"上海",// 城市
              district:"浦东新区",// 区
              address:"航头镇航头路18号传智播客 3号楼",// 街道地址
              zipCode:"210000", // 邮编
              isDefault: true
          },
          {
              id: 2,
              name:"张三",// 收件人姓名
              phone:"13600000000",// 电话
              state:"北京",// 省份
              city:"北京",// 城市
              district:"朝阳区",// 区
              address:"天堂路 3号楼",// 街道地址
              zipCode:"100000", // 邮编
              isDefault: false
          }
      ];
  },
  // mounted () {
  //     this.qrcode();
  // },
  methods: {
      /*getOrdersInfo(ordersId){
          getOrders(ordersId).then(response => {
              this.ordersInfo = response.data;
              const qrcode = this.ordersInfo.payQrCode;
              this.showQrcode(qrcode);
          });
      }*/
  }
};
</script>
<link rel="stylesheet/scss" href="../../../assets/styles/shopping/main.css">
<link rel="stylesheet/scss" href="../../../assets/styles/shopping/layui.css">
<script type="text/javascript" src="../../../assets/styles/res/layui/layui.js"></script>
<script type="text/javascript" src="../../../utils/plugins/sui/sui.min.js"></script>
