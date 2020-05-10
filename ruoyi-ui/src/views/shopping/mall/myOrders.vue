<template>
  <div>
    <headerBar :headerBar="headerBar"/>

    <div class="content content-nav-base commodity-content">
      <div class="commod-cont-wrap">
        <div class="commod-cont w1200 layui-clear">

          <div class="left-nav">
            <div class="title">订单中心</div>
              <div class="list-box">
                <dl>
                  <dt style="display: none;"></dt>
                  <dd><a>我的订单</a></dd>
                </dl>
              </div>
          </div>
          <div class="right-cont-wrap">

            <div class="body">
              <div class="table-title">
                <table class="sui-table  order-table">
                    <thead>
                      <tr>
                        <th width="35%">宝贝</th>
                        <th width="5%">单价</th>
                        <th width="5%">数量</th>
                        <!--<th width="8%">商品操作</th>-->
                        <th width="10%">实付款</th>
                        <th width="10%">交易状态</th>
                        <th width="10%">交易操作</th>
                      </tr>
                    </thead>
                </table>
              </div>
              <div class="order-detail">
                <div class="orders">

                  <!--order1-->
                  <div v-for="(o, i) in myOrders" :key="i">
                    <div class="choose-title">
                      <!--<label data-toggle="checkbox" class="checkbox-pretty ">
                        <input type="checkbox" checked="checked" />
                      </label>-->
                        <span>订单编号：{{o.ordersId}} <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2474520147&site=qq&menu=yes">联系客服</a></span>
                    </div>
                    <table class="sui-table table-bordered order-datatable">
                      <tbody>
                        <!--<tr v-for="(d,j) in o.orderDetails" :key="j">-->
                        <tr>
                          <td width="35%">
                            <div class="typographic"><!--<img width="70px" height="70px" :src="d.image"/>-->
                              <a target="_blank" :href="'http://localhost/detail/' + o.goodId" class="block-text">包邮 {{o.goodName}}</a>
                              <!--<span class="guige" v-for="(v,k) in JSON.parse(d.ownSpec)" :key="k">{{k+':'+ v}}</span>-->
                            </div>
                          </td>
                          <td width="5%" class="center">
                            <ul class="unstyled">
                              <li>¥{{o.price}}</li>
                            </ul>
                          </td>
                          <td width ="5%" class="center">{{o.num}}</td>
                          <!--<td width="8%" class="center" :rowspan="o.orderDetails.length" v-if="j === 0">
                            <ul class="unstyled" v-if="o.status === 3">
                              <li>已发货</li>
                              <li><a>退货/退款</a></li>
                            </ul>
                          </td>-->
                          <td width="10%" class="center"  >
                            <ul class="unstyled">
                              <li>¥{{o.priceSum}}</li>
                              <li>（含运费：￥{{o.freight == 0 ? '0.00' : o.freight}}）</li>
                            </ul>
                          </td>
                          <td width="10%" class="center" >
                            <ul class="unstyled">
                              <li v-if="o.status === '0'">等待买家付款</li>
                              <li v-if="o.status === '1'">已付款</li>
                              <li v-if="o.status === '2'">已发货</li>
                              <li v-if="o.status === '3'">交易成功</li>
                              <li v-if="o.status === '4'">订单关闭</li>
                              <!--<li><a href="home-orderDetail.html" class="btn">订单详情 </a></li>-->
                            </ul>
                          </td>
                          <td width="10%" class="center">
                            <ul class="unstyled">
                              <li v-if="o.status === '0'"><a href="#" class="sui-btn btn-info">立即付款</a></li>
                              <li v-if="o.status === '0'"><a href="javascript:void(0)">取消订单</a></li>
                              <li v-if="o.status === '1'"><a href="#" class="sui-btn btn-info">提醒发货</a></li>
                              <li v-if="o.status === '2'"><a href="#" class="sui-btn btn-info">确认收货</a></li>
                              <li v-if="o.status === '2'">4天23时后自动确认</li>
                            </ul>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>

                  <div class="choose-order">
                    <div class="sui-pagination pagination-large top-pages">
                      <ul>
                        <li class="prev disabled"><a href="#">上一页</a></li>

                        <li class="next"><a href="#">下一页</a></li>
                      </ul>
                    </div>
                  </div>

                </div>
                <!--<div class="choose-order">
                  <div class="sui-pagination pagination-large top-pages">
                    <ul>
                      <li class="prev disabled"><a href="#">«上一页</a></li>
                      <li class="active"><a href="#">1</a></li>
                      <li><a href="#">2</a></li>
                      <li><a href="#">3</a></li>
                      <li class="dotted"><span>...</span></li>
                      <li class="next"><a href="#">下一页»</a></li>
                    </ul>
                    <div><span>共10页&nbsp;</span><span>
                                            到
                                            <input type="text" class="page-num"><button class="page-confirm"
                                                                                        onclick="alert(1)">确定</button>
                                            页</span></div>
                  </div>
                </div>-->
              </div>
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
  @import "../../../assets/styles/leyou/css/pages-seckillOrder.css";
</style>
<script>
import Cookies from "js-cookie";
import { listOrders} from "@/api/shopping/myOrders";
import headerBar from "./headerBar";
import $ from 'jquery';

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
      myOrders:[],
    };
  },
  created() {
      this.getList();
      // console.log($('.mallSearch'));
      // $('.mallSearch').attr('style','display:none;');
  },
  methods: {
      /** 查询订单列表 */
      getList() {
          this.loading = true;
          listOrders(this.queryParams).then(response => {
              this.myOrders = response.rows;
              this.loading = false;
          });
      },
  }
};
</script>
<link rel="stylesheet/scss" href="../../../assets/styles/shopping/main.css">
<link rel="stylesheet/scss" href="../../../assets/styles/shopping/layui.css">
<script type="text/javascript" src="../../../assets/styles/res/layui/layui.js"></script>
