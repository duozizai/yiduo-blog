<template>
  <div>
    <!-- 消息 -->
    <Message />
    <!-- <navbar class="div-less"/> -->
    <!-- <leftbar  class="div-less"/> -->
    <!-- <main-app class="div-less main-app"/>
    <flootbar />-->
    <el-container>
      <el-header>
        <navbar class="div-less" />
      </el-header>
      <el-main>
        <main-app class="div-less main-app" />
      </el-main>
      <el-footer>
        <flootbar />
      </el-footer>
    </el-container>
  </div>
</template>
<script>
import Navbar from "@/views/layout/components/Navbar";
import Flootbar from "@/views/layout/components/Flootbar";
import MainApp from "@/views/layout/components/MainApp";
import Message from "@/components/message/alert";
// import Leftbar from '@/views/layout/components/Leftbar'
// import { Navbar, Flootbar, MainApp, Leftbar } from './components'
export default {
  name: "Layout",
  components: {
    Navbar,
    // Leftbar,
    MainApp,
    Flootbar,
    Message
  }
};
</script>
<style >
body {
  margin: 0px;
}
.div-less {
  padding: 10px;
}
.main-app {
  margin-bottom: 42px;
}
</style>


