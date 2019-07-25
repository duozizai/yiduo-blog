<template>
  <div class="block">
    <!-- 列表 -->
    <el-table
      :data="page.rows"
      :default-sort="{prop: 'createdAt', order: 'descending'}"
      @sort-change="handleSortChange"
      style="width: 100%"

    >
      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column sortable="custom" prop="email" label="登陆邮箱" width="180"></el-table-column>
      <el-table-column prop="phone" label="联系人电话" width="180"></el-table-column>
      <el-table-column sortable="custom" prop="createdAt" label="创建时间"></el-table-column>
      <el-table-column prop="setItem"  label="操作" > <template slot-scope="scope"><el-button  type="text" @click="showDetail(scope.row.id)" class="button">查看详情</el-button></template></el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="this.page.pageNumber"
      :page-sizes="[2, 200, 300, 400]"
      :page-size="this.page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="this.page.total"
    ></el-pagination>
  </div>
</template>

<script>
import { usersList } from "@/server/login.js";
export default {
  data() {
    return {
      page: {
        total: 0,
        pageNumber: 1,
        pageSize: 2,
        totalPages: 0,
        rows: []
      },
      sort: "createdAt",
      order: "desc"
    };
  },
  created: function() {
    //查询 users
    const currentPage = {
      pageNumber: this.page.pageNumber,
      pageSize: this.page.pageSize,
      order: this.order,
      sort: this.sort
    };
    this.initUsers(currentPage);
  },
  methods: {
    initUsers(currentPage) {
      usersList(currentPage)
        .then(data => {
          this.page = data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    handleSizeChange(val) {
      this.page.pageSize = val;
      const currentPage = {
        pageNumber: this.page.pageNumber,
        pageSize: this.page.pageSize
      };
      this.initUsers(currentPage);
    },
    handleCurrentChange(val) {
      this.page.pageNumber = val;
      const currentPage = {
        pageNumber: this.page.pageNumber,
        pageSize: this.page.pageSize,
        order: this.order,
        sort: this.sort
      };
      this.initUsers(currentPage);
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
      this.initUsers(currentPage);
    },showDetail(id){
      this.$router.push({ path: `/users/${id}` });
    }
  }
};
</script>