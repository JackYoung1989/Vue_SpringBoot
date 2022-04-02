<template>

  <div class="home" style="padding: 10px;flex: 1">

    <div style="padding: 10px 0px;">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>

    <div style="margin: 10px 0;">
      <el-input v-model="search" placeholder="请输入..."  style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px; " @click="load">查询</el-button>
    </div>

    <el-table :data="tableData" stripe border style="width: 100%;">
      <el-table-column prop="id" label="ID" sortable/>
      <el-table-column prop="name" label="名字" />
      <el-table-column prop="price" label="价格" />//后台如果是下划线，前台对应驼峰，mybatis plus框架做的
      <el-table-column prop="author" label="作者" />
      <el-table-column prop="createTime" label="出版时间" />
      <el-table-column fixed="right" label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="Are you sure to delete this?" @confirm="delete2(scope.row.id)">
            <template #reference>
              <el-button type="danger" >删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div class="demonstration " style="margin: 10px 0;"></div>
    <el-pagination
        v-model:currentPage="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[5, 10, 20, 30, 40]"
        :small="small"
        :disabled="false"
        :background="true"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>

  <el-dialog v-model="dialogVisible" title="Tips" width="30%" >
    <el-form :model="form" label-width="120px"> <!-- form绑定一个model = "form" -->
      <el-form-item label="名称" >
        <el-input v-model="form.name" style="width: 80%"/>
      </el-form-item>
      <el-form-item label="价格" >
        <el-input v-model="form.price" style="width: 80%"/>
      </el-form-item>
      <el-form-item label="作者">
        <el-input v-model="form.author"  style="width: 80%"/>
      </el-form-item>
      <el-form-item label="出版时间">
        <el-date-picker value-format="YYYY-MM-DD" type="date" v-model="form.createTime" style="width: 80%" clearable></el-date-picker>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script >
// @ is an alias to /src

import request from "../utils/request";

export default {
  name: 'Home',
  components: {

  },
  created() {
    this.load()
  },
  methods: {
    delete2(id) {
      console.log("delete")
      request.delete("/book/" + id).then(res => {
        if (res.code == "0") {
          this.$message({
            type : "success",
            message : "删除成功"
          })
        } else {
          this.$message({
            type : "error",
            message : res.msg
          })
        }
        this.load()
      })
    },
    load() {
      request.get("/book", {
        params: {
          pageNum: this.currentPage, pageSize: this.pageSize, search: this.search
        }
      }).then(res => {
        console.log("load方法返回的数据" + res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add() {
      this.dialogVisible = true;
      this.form = {};
    },
    save() {
      if (this.form.id) {// 更新
        request.put("/book", this.form).then(res => {
          console.log(res)
          if (!res.code) {
            this.$message({
              type: "success",
              message: "更新成功"
            })
          } else {
            this.$message({
              type: "failed",
              message: "更新失败"
            })
          }
          this.load()
          this.dialogVisible = false
        })
      } else {
        request.post("/book", this.form).then(res => {
          console.log(res)
          console.log("返回的数据是：" + res.data)
          if (!res.data) {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load()
          this.dialogVisible = false
        })
      }
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
    },
    handleSizeChange() {
      this.load()
    },
    handleCurrentChange() {
      this.load()
    },
  },

  data() {
    return {
      form : {},
      search: "",
      dialogVisible: false,
      currentPage : 1,
      pageSize : 10,
      small : true,
      total : 100,
      tableData : []
    }
  }
}
</script>
