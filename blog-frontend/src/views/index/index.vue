<template>
<div>
  <!-- 卡片 -->
  <el-row >
    <el-col :span="5" v-for="(o, index) in this.page.rows" :key="o" :offset="index > 0 ? 1 : 0" style="margin-left: 3px">
      <el-card :body-style="{ padding: '4px' }">
        <img src="" class="image">
        <div style="padding: 14px;">
          <span>{{o.email}}</span>
          <div class="bottom clearfix">
            <time class="time">{{ o.createdAt }}</time>
            <el-button type="text" class="button">查看详情</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
  
   <!-- 分页 -->
<el-pagination class="div-main"
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="this.page.pageNumber"
        :page-sizes="[2, 200, 300, 400]"
        :page-size="this.page.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="this.page.total">
      </el-pagination>
</div>
</template>

<style>
  .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
  .div-main{
    float:right;
    margin: 10px;
  }
</style>
<script>
import { blogList,usersList } from '@/server/login.js';
export default {
  data() {
    return {
      currentDate: new Date(),
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
     const currentPage =  {pageNumber: this.page.pageNumber, pageSize: this.page.pageSize,order: this.order,
            sort: this.sort}
      this.initBlog(currentPage)
  },methods:{
    initBlog(currentPage){
      usersList(currentPage)
      .then(data => {
        this.page = data
        console.log(this.page)
      }).catch(err => {
        console.log(err)
      })
    },
    handleSizeChange(val) {
      this.page.pageSize = val;
      const currentPage =  {pageNumber:this.page.pageNumber, pageSize: this.page.pageSize}
      this.initBlog(currentPage)
    },
    handleCurrentChange(val) {
      this.page.pageNumber = val;
      const currentPage =  
      {
        pageNumber:this.page.pageNumber,
        pageSize: this.page.pageSize,
        }
      this.initBlog(currentPage)
    }
  }
}
  </script>