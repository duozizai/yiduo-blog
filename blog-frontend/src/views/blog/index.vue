<template>

    <div class="block">
        <el-button type="primary" @click="add">添加</el-button>
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
        prop="title"
        label="标题"
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
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="prev, pager, next"
        :page-size="page.pageSize"
        :current-page="page.pageNumber"
        :total="page.total">
    </el-pagination>
    </div>
</template>

<script>
    import { blogList } from '@/server/login.js';
    export default {
      data() {
        return {
          page:{
            total: 0,
            pageNumber: 1,
            pageSize: 10,
            totalPages: 0,
            rows:[]
          },
            sort: "createdAt",
            order: "desc"
        }
      },created: function () {
        //查询 users
        const currentPage =  {pageNumber: this.page.pageNumber, pageSize: this.page.pageSize,order: this.order,
            sort: this.sort}
        this.initBlog(currentPage)
      },methods:{
        initBlog(currentPage){
          blogList(currentPage).then(data => {
           this.page = data
          })
        },
        add(){
            //跳转到添加页面
            this.$router.push({path: '/blogs/add'});
        },
        handleCurrentChange(val) {
            this.page.pageNumber = val;
            const currentPage = {
                pageNumber: this.page.pageNumber,
                pageSize: this.page.pageSize,
                order: this.order,
                sort: this.sort
            };
            this.initBlog(currentPage);
        },
        handleSortChange(val) {
            this.sort = val.prop;
            this.order = val.order === "descending" ? "desc" : "asc";
            const currentPage = {
                pageNumber: this.page.pageNumber,
                pageSize: this.page.pageSize,
                order: this.order,
                sort: this.sort
            };
            this.initBlog(currentPage);
        }
      }
    }
  </script>
