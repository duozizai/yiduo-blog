<template>

    <div>
      <div class="login-form">
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
        <el-input v-model="ruleForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
        </el-form-item>


        <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button type="primary" @click="singUp()">注册</el-button>
        </el-form-item>
        </el-form>
      </div>
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
        username:'',
      },
      rules: {
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { max: 30,min:5, message: '8~30', trigger: 'blur' },

        ],
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
    ...mapMutations('message',[
        'setMessage'
    ]),
    submitForm(formName) {
      const $this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 发送post 请求
          const _data = loginData({
              email: $this.ruleForm.username,
              password: $this.ruleForm.password
          });
          _data.then(res => {
              //设置 token
              localStorage.setItem('token',res.data.token)
              $this.setLogined(res.data.token);
              //设置用户信息
              const _backUrl = this.$route.query.backUrl ? this.$route.query.backUrl : '/';
              this.$router.push({path: _backUrl});
          })
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    singUp(){
      this.$router.push({path: '/singup'});
    }

  }
}
</script>
<style>
    div .title{
        font-size: 30px;
    }
    .login-form {
      position: absolute;
      top: 50%;
      left: 50%;
      width: 400px;
      margin-left: -200px;
      margin-top: -94px;
    }
</style>
