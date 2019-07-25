<template>
  <div class="message-style">
    <el-alert
      v-if="this.$store.state.message.status"
      :title="title"
      :type="type"
      prop="center=true"
      effect="dark"
    ></el-alert>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  data() {
    return {
      status: this.$store.state.message.status,
      type: this.$store.state.message.type,
      title: this.$store.state.message.title
    };
  }
};
</script>
<style>
.message-style {
  text-align: center; /*让div内部文字居中*/
  width: 500px;
  left: 50%;
  margin-left: -150px;
}
</style>





