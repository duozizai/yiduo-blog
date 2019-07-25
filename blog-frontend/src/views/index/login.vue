<template>
    
    <div>
      <Message/>
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
        <el-button type="primary" @click="resetForm('ruleForm')">注册</el-button>
        </el-form-item>
        </el-form>
      </div>
    </div>
</template>

<script>
import { loginData } from '@/server/login.js';
import Message from '@/components/message/alert'
import { mapMutations } from 'vuex';

export default {
   components: {
     Message
   },
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
    ...mapMutations('message',[
        'setMessage'
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
          _data.then(res => {
            if(res.status){
              //设置 token
              localStorage.setItem('token',res.data.token)
              $this.setLogined(res.data.token);
              //设置用户信息
              const _backUrl = this.$route.query.backUrl?this.$route.query.backUrl:'/';
              console.log(`登陆成功 跳转到 ${_backUrl}`)
              this.$router.push({path: `${_backUrl}`});
            }else{
             this.setMessage({status:true,title:"服务器错误",type:"error"});
            };
          }).catch( err => {
            console.log(err)
            this.setMessage({status:true,title:"服务器错误",type:"error"});
            console.log(this.$store.state.message)
          }) ;
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
    .login-form {
      position: absolute;
      top: 50%;
      left: 50%;
      width: 400px;
      margin-left: -200px;
      margin-top: -94px;
    }
</style>
