<template>
<el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="标题">
        <el-input v-model="form.title"></el-input>
    </el-form-item>

    <el-form-item label="详情">
        <el-input type="textarea" v-model="form.detail"></el-input>
    </el-form-item>
    <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button>取消</el-button>
    </el-form-item>
</el-form>
</template>
<script>
    import { blogAdd } from '@/server/login.js';
    export default {
        data() {
            return {
                form: {
                    title: '',
                    detail: '',
                }
            }
        },
        methods: {
            onSubmit() {
               this.add(this.form)
            },
            add(blog){
                blogAdd(blog).then(data =>{
                    console.log("成功")
                    this.$router.push({path: '/blogs'});
                }).catch(error =>{
                    this.$message({
                        type: 'error',
                        message: `${JSON.stringify(error)}`
                    })
                })
            }
        }
    }
</script>
