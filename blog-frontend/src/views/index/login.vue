<template>
    <div>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="用户名" prop="username">
    <el-input v-model="ruleForm.username" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="密码" prop="password">
    <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
  </el-form-item>
  
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
    <el-button type="primary" @click="resetForm('ruleForm')">注册</el-button>
  </el-form-item>
</el-form>

    </div>

</template>

<script>
import { loginData } from '@/server/login.js';
import { mapMutations } from 'vuex';
export default {
  data() {
    return {
      ruleForm: {
        password: '',
        username: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { max: 30,min:5, message: '8~30', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { max: 30,min:5, message: '5~30', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    ...mapMutations('auth',[
            'setLogined'
        ]),
    submitForm(formName) {
      const $this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log($this.ruleForm)
          // 发送post 请求
          const _data = loginData({
              email: $this.ruleForm.username,
              password: $this.ruleForm.password
          });
          console.log(_data)
          console.log(_data.PromiseValue)
          _data.then(res => {
            if(res.status){
              // this.$toast('登陆成功');
              // $this.setLogined(res.data.token);
              // $this.$cookies.set("user_session",res.data.token)
              const _backUrl = this.$route.query.backUrl?this.$route.query.backUrl:'/index';
              $this.$router.push(`${_backUrl}`);
            }else{
                // this.$toast(_data.message);
            };
          });
         
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    
  }
}
</script>
<style>
    div .title{
        font-size: 30px;
    }
</style>
