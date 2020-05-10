<template>
  <div>
    <headerBar :headerBar="headerBar"/>

    <div class="content content-nav-base datails-content">
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

      <div class="data-cont-wrap w1200">
        <div class="crumb">
          <a href="javascript:;">首页</a>
          <span>></span>
          <a href="javascript:;">所有商品</a>
          <span>></span>
          <a href="javascript:;">产品详情</a>
        </div>
        <div class="product-intro layui-clear">
          <div class="preview-wrap">
            <a href="javascript:;"><img :src="'/dev-api/'+goodDetailInfo.pic" style="width:402px;height:402px;"></a>
          </div>
          <div class="itemInfo-wrap">
            <div class="itemInfo">
              <div class="title">
                <h4>{{goodDetailInfo.goodName}}</h4>
                <span><i class="layui-icon layui-icon-rate-solid"></i>收藏</span>
              </div>
              <div class="summary">
                <!--<p class="reference"><span>参考价</span> <del>￥{{goodDetailInfo.price}}</del></p>-->
                <p class="activity"><span>价格</span><strong class="price"><i>￥</i>{{goodDetailInfo.price}}</strong></p>
                <p class="address-box"><span>送&nbsp;&nbsp;&nbsp;&nbsp;至</span><strong class="address">江西&nbsp;&nbsp;南昌&nbsp;&nbsp;东湖区</strong></p>
              </div>
              <div class="choose-attrs">
                <div class="color layui-clear"><span class="title">颜&nbsp;&nbsp;&nbsp;&nbsp;色</span> <div class="color-cont"><span class="btn">白色</span> <span class="btn active">粉丝</span></div></div>
                <div class="number layui-clear"><span class="title">数&nbsp;&nbsp;&nbsp;&nbsp;量</span><div class="number-cont"><span class="cut btn">-</span><input onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="4" type="" name="" value="1"><span class="add btn">+</span></div></div>
              </div>
              <div class="choose-btns">
                <button class="layui-btn layui-btn-primary purchase-btn" @click="payNow">立刻购买</button>
                <button class="layui-btn  layui-btn-danger car-btn"><i class="layui-icon layui-icon-cart-simple"></i>加入购物车</button>
              </div>
            </div>
          </div>
        </div>
        <div class="layui-clear">
          <!--<div class="aside">
            <h4>热销推荐</h4>
            <div class="item-list">
              <div class="item">
                <img src="../../../assets/styles/res/static/img/details_img2.jpg">
                <p><span>可爱宝宝粉色连体秋裤</span><span class="pric">￥99.00</span></p>
              </div>
              <div class="item">
                <img src="../../../assets/styles/res/static/img/details_img2.jpg">
                <p><span>可爱宝宝粉色连体秋裤</span><span class="pric">￥99.00</span></p>
              </div>
              <div class="item">
                <img src="../../../assets/styles/res/static/img/details_img2.jpg">
                <p><span>可爱宝宝粉色连体秋裤</span><span class="pric">￥99.00</span></p>
              </div>
              <div class="item">
                <img src="../../../assets/styles/res/static/img/details_img2.jpg">
                <p><span>可爱宝宝粉色连体秋裤</span><span class="pric">￥99.00</span></p>
              </div>
              <div class="item">
                <img src="../../../assets/styles/res/static/img/details_img2.jpg">
                <p><span>可爱宝宝粉色连体秋裤</span><span class="pric">￥99.00</span></p>
              </div>
              <div class="item">
                <img src="../../../assets/styles/res/static/img/details_img2.jpg">
                <p><span>可爱宝宝粉色连体秋裤</span><span class="pric">￥99.00</span></p>
              </div>
            </div>
          </div>-->
          <div class="detail">
            <h4>详情</h4>
            <div class="item" id="goodDetailSummary">
              <!--{{goodDetailInfo.summary}}
              <img src="../../../assets/styles/res/static/img/details_imgbig.jpg">-->
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
import { getGoodDetail, placeAnOrder} from "@/api/shopping/detail";
import $ from 'jquery';
import headerBar from "./headerBar";

export default {
  name: "Detail",
  components: { headerBar },
  props: {
      headerBar: {
          type: Object
      }
  },
  data() {
    return {
        goodDetailInfo:{}
      // isActive: true,
      // loading: false,
      // redirect: undefined,
    };
  },
  // watch: {
  //   $route: {
  //     handler: function(route) {
  //       this.redirect = route.query && route.query.redirect;
  //     },
  //     immediate: true
  //   }
  // },
  created() {
      const goodId = this.$route.params && this.$route.params.goodId;
      this.goodDetail(goodId);
  },
  methods: {
    // dlClick(event){
    //     console.log(event.currentTarget);
    //     console.log(event.currentTarget);
    //
    // },
      goodDetail(goodId){
          getGoodDetail(goodId).then(response => {
              this.goodDetailInfo = response.data;
              document.getElementById("goodDetailSummary").innerHTML = this.goodDetailInfo.summary;
              $('#goodDetailSummary img').attr('style', 'width:100%');
          });
      },
      payNow(){
          const goodId = this.$route.params && this.$route.params.goodId;
          placeAnOrder(goodId).then(response => {
              window.location.href = "../pay/" + goodId;
          });
      }
  }
};
</script>
<link rel="stylesheet/scss" href="../../../assets/styles/shopping/main.css">
<link rel="stylesheet/scss" href="../../../assets/styles/shopping/layui.css">
<script type="text/javascript" src="../../../assets/styles/res/layui/layui.js"></script>
<script>
    layui.config({
        base: '../../../assets/styles/res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
    }).use(['mm','jquery'],function(){
        var mm = layui.mm,$ = layui.$;
        var cur = $('.number-cont input').val();
        $('.number-cont .btn').on('click',function(){
            if($(this).hasClass('add')){
                cur++;

            }else{
                if(cur > 1){
                    cur--;
                }
            }
            $('.number-cont input').val(cur)
        })

    });
</script>
