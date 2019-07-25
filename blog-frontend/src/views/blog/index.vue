<template>

    <div class="block">
        <!-- 列表 -->
    <el-table
      :data="page.rows"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="email"
        label="登陆邮箱"
        width="180">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="联系人电话"
        width="180">
      </el-table-column>
      <el-table-column
        prop="createdAt"
        label="创建时间">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
        background
        layout="prev, pager, next"
        :total="this.page.total">
    </el-pagination>
    </div>
</template>

<script>
    import { blogList,usersList } from '@/server/login.js';
    export default {
      data() {
        return {
          page:{
            total: 0,
            pageNumber: 1,
            pageSize: 20,
            totalPages: 0,
            rows:[]
          }
        }
      },created: function () {
        //查询 users
        this.initBlog()
      },methods:{
        initBlog(){
          //
          usersList().then(data => {
           this.page = data
          }).catch(err => {
              console.log(err)
          })
        }
      }
    }
  </script>