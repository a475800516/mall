<template>
  <div>
    <div class="site-nav-bg">
      <div class="site-nav w1200">
        <p class="sn-back-home">
          <i class="layui-icon layui-icon-home"></i>
          <a href="http://localhost/toShopping">首页</a>
        </p>
        <div class="sn-quick-menu">
          <div class="login"><a href="#">{{username}}</a></div>
          <div class="sp-cart"><a href="http://localhost/myOrders">我的订单</a></div>
          <!--<div class="sp-cart"><a href="shopcart.html">购物车</a><span>2</span></div>-->
        </div>
      </div>
    </div>

    <div class="header">
      <div class="headerLayout w1200">
        <div class="headerCon">
          <h1 class="mallLogo">
            <a href="#" title="母婴商城">
              <img src="../../../assets/styles/res/static/img/logo.png">
            </a>
          </h1>
          <div class="mallSearch">
            <form action="" class="layui-form" novalidate>
              <input type="text" name="title" required  lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入需要的商品">
              <button class="layui-btn" lay-submit lay-filter="formDemo">
                <i class="layui-icon layui-icon-search"></i>
              </button>
              <input type="hidden" name="" value="">
            </form>
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
import { getInfo } from '@/api/login'
import {} from "@/api/shopping/mall";

export default {
  name: "Header",
  data() {
    return {
    };
  },
  created() {
      this.getCookie();
  },
  methods: {
      getCookie() {
          this.username = Cookies.get("username");
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
