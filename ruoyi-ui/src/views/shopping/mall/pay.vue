<template>
  <div>
    <headerBar :headerBar="headerBar"/>

    <div class="content content-nav-base commodity-content">
      <div class="commod-cont-wrap">

        <div class="commod-cont w1200 layui-clear">
          <div class="checkout py-container  pay">
            <div class="checkout-tit">
              <h4 class="fl tit-txt">
                <span class="success-icon"></span>
                <span class="success-info">订单提交成功，请您及时付款！订单号：{{ordersInfo.ordersId}}</span>
              </h4>
              <span class="fr">
						<em class="sui-lead">应付金额：</em>
						<em class="orange money">￥{{ordersInfo.priceSum}}</em>元</span>
              <div class="clearfix"></div>
            </div>
            <div class="checkout-steps">
              <div class="fl weixin">支付宝支付</div>
              <div class="fl sao">

                <div class="fl code">
                  <div id="qrImage"></div>
                  <div class="saosao">
                    <p>请使用支付宝扫一扫</p>
                    <p>扫描二维码支付</p>
                  </div>
                </div>
                <div class="fl phone">

                </div>

              </div>
              <div class="clearfix"></div>
              <!--<p>
                <a href="pay.html" target="_blank">> 其他支付方式</a>
              </p>-->
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
  @import "../../../assets/styles/leyou/css/pages-weixinpay.css";
</style>
<script>
import { getOrders} from "@/api/shopping/pay";
import headerBar from "./headerBar";
import QRCode from 'qrcodejs2'

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
      ordersInfo:{
      }
    };
  },
  created() {
      const ordersId = this.$route.params && this.$route.params.ordersId;
      this.getOrdersInfo(ordersId);
  },
  // mounted () {
  //     this.qrcode();
  // },
  methods: {
      //生成支付宝二维码
      showQrcode(qrcode) {
          let qrcodeTemp = new QRCode('qrImage', {
              width: 260,
              height: 260,
              text: qrcode, // 二维码地址
              colorDark : "#000",
              colorLight : "#fff",
          })
      },
      getOrdersInfo(ordersId){
          getOrders(ordersId).then(response => {
              this.ordersInfo = response.data;
              const qrcode = this.ordersInfo.payQrCode;
              this.showQrcode(qrcode);
          });
      }
  }
};
</script>
<link rel="stylesheet/scss" href="../../../assets/styles/shopping/main.css">
<link rel="stylesheet/scss" href="../../../assets/styles/shopping/layui.css">
<script type="text/javascript" src="../../../assets/styles/res/layui/layui.js"></script>
