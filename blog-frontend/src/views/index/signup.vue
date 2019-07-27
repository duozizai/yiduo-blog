<template>

    <div>
      <div class="login-form">
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="邮箱" prop="email">
          <el-input type="email" v-model="ruleForm.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
        <el-input v-model="ruleForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="passwordConfirm">
          <el-input type="password" v-model="ruleForm.passwordConfirm" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="姓名" prop="name">
          <el-input v-model="ruleForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input  type="number" v-model="ruleForm.phone" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
          <el-button type="primary" @click="backLogin()">back</el-button>
        </el-form-item>
        </el-form>
      </div>
    </div>
</template>

<script>
import { loginData,signupData } from '@/server/login.js';
import { mapMutations } from 'vuex';
export default {
  data() {
    return {
      ruleForm: {
        password: '',
        passwordConfirm:'',
        email: '',
        username:'',
        phone:'',
      },
      rules: {
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { max: 30,min:5, message: '8~30', trigger: 'blur' },
          { email: true, message: '请输入正确的邮箱', trigger: 'blur' },
        ],
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { max: 30,min:5, message: '8~30', trigger: 'blur' },
          { email: true, message: '请输入正确的邮箱', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { max: 30,min:5, message: '5~30', trigger: 'blur' }
        ],
        passwordConfirm: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { max: 30,min:5, message: '5~30', trigger: 'blur' }
        ],
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
          const _data = signupData(this.ruleForm);
          _data.then(res => {
              //返回登录页面
              const _backUrl = this.$route.query.backUrl ? this.$route.query.backUrl : '/';
            this.$message({
              type: 'success',
                      message: `${this.ruleForm.email} 注册成功`
            });
              this.$router.push({path: "/login",query: {
                  backUrl: _backUrl
              }});
          })
        } else {
          return false;
        }
      });
    },
    backLogin(){
      this.$router.push({path: '/login'});
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
    .login-form {
      position: absolute;
      top: 50%;
      left: 50%;
      width: 400px;
      margin-left: -200px;
      margin-top: -94px;
    }
</style>
