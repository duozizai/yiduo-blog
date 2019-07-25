<template>
  <div>
    <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>{{this.user.username}}</span>
          <div class="el-page-header__left" @click="goBack" style="float: right;">
            <i class="el-icon-back"></i>
            <div class="el-page-header__title">
              <slot name="title">返回</slot>
            </div>
          </div>
    </div>
    <div v-for="o in this.user" :key="o" class="text item">
      {{o}}
    </div>
  </el-card>
  </div>
</template>

<script>
import { userDetail } from "@/server/login.js";
export default {
  data() {
    return {
      user:{},
      sort: "createdAt",
      order: "desc",
      id: 0
    };
  },
  created: function() {
    //查询 users
    //获取当前路径中的id
    this.id = this.$route.params.id
    console.log(this.$route.params)
    this.initUser(this.id);
  },
  methods: {
    initUser(id) {
      userDetail(id)
        .then(data => {
          this.user = data;
          console.log(data)
        })
    },goBack() {
      this.$router.push({ path: `/users` });
    }
  }
};
</script>

<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 480px;
  }
</style>