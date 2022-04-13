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
      <el-table-column prop="username" label="用户名" />
      <el-table-column prop="nickName" label="昵称" />//后台如果是下划线，前台对应驼峰，mybatis plus框架做的
      <el-table-column prop="age" label="年龄" />
      <el-table-column prop="sex" label="性别" />
      <el-table-column prop="address" label="地址" />
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
      <el-form-item label="用户名" >
        <el-input v-model="form.username" style="width: 80%"/>
      </el-form-item>
      <el-form-item label="昵称" >
        <el-input v-model="form.nickName" style="width: 80%"/>
      </el-form-item>
      <el-form-item label="年龄">
        <el-input v-model="form.age"  style="width: 80%"/>
      </el-form-item>
      <el-form-item label="性别" style="width: 80%">
        <el-radio v-model="form.sex" label="男" size="large">男</el-radio>
        <el-radio v-model="form.sex" label="女" size="large">女</el-radio>
        <el-radio v-model="form.sex" label="未知" size="large">未知</el-radio>
      </el-form-item>
      <el-form-item label="地址">
        <el-input type="textarea" v-model="form.address" style="width: 80%" />
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
      request.delete("/user/" + id).then(res => {
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
      request.get("/user", {
        params: {
          pageNum: this.currentPage, pageSize: this.pageSize, search: this.search
        }
      }).then(res => {
        console.log(res)
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
        request.put("/user", this.form).then(res => {
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
        request.post("/user", this.form).then(res => {
          console.log(res)
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
