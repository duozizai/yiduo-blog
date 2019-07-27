<template>
<div>

  <el-carousel indicator-position="outside">
    <el-carousel-item v-for="item in 4" :key="item">
      <h3>{{ item }}</h3>
    </el-carousel-item>
  </el-carousel>
  <!--  分割线 -->
  <el-divider></el-divider>
  <!-- 卡片 -->
  <el-row >
    <el-col :span="8" v-for="(o, index) in this.page.rows" :key="o" :offset="index > 0 ? index : 0" style="margin-left: 3px">
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
<el-pagination v-if="!isShow" class="div-main"
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

<script>
import { blogList,usersList } from '@/server/login.js';
export default {
  data() {
    return {
      currentDate: new Date(),
      page:{
          total: 0,
          pageNumber: 1,
          pageSize: 2,
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
    isShow(){
      return this.page.pageNumber == 1 && !this.page.rows.length > 0 ;
    },
    initBlog(currentPage){
      blogList(currentPage)
      .then(data => {
        this.page = data
        console.log(data)
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
<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

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
