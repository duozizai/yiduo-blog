<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
  >
    <el-menu-item index="/">首页</el-menu-item>
    <el-menu-item index="/blogs">我的文章列表</el-menu-item>
    <el-menu-item index="/users">用户列表</el-menu-item>
    <el-menu-item index="3">
      <el-badge :value="12" class="item">消息中心</el-badge>
    </el-menu-item>
    <el-submenu style="float: right;">
      <template style="float: right;" v-if="isShowUserDetail" slot="title">{{this.userDetail}}</template>
      <el-menu-item>个人中心</el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
import { loginUserData } from "@/server/login.js";
import { mapMutations } from "vuex";

export default {
  data() {
    return {
      activeIndex: "/",
      user: {
        username: "",
        email: ""
      },
      isShowUserDetail: false,
      userDetail: ""
    };
  },
  created: function() {
    this.getLoginUser();
    this.initPath();
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      // if(key != null){
      this.activeIndex = key;
      this.$router.push({ path: key });
      // }else{
      //   this.activeIndex = this.activeIndex;
      // }
    },
    getLoginUser() {
      loginUserData()
        .then(data => {
          this.user.username = data.username;
          this.user.email = data.email;
          this.isShowUserDetail = this.user.username === "" ? false : true;
          this.userDetail = `用户名: ${this.user.username} email: ${this.user.email}`;
        })
        .catch(err => {
          console.log(err);
        });
    },
    initPath() {
      this.activeIndex = this.$route.path;
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.header {
  background-color: black;
  color: white;
  text-align: center;
  padding: 20px;
}
</style>
