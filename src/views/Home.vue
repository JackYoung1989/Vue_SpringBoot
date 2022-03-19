<template>

  <div class="home" style="padding: 10px;">

    <div style="padding: 10px 0px;">
      <el-button type="primary">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>

    <div style="margin: 10px 0;">
      <el-input v-model="search" placeholder="请输入..." :prefix-icon="Search" style="width: 20%"></el-input>
      <el-button type="primary" style="margin-left: 5px; ">查询</el-button>
    </div>

    <el-table :data="tableData" stripe border style="width: 100%;">
      <el-table-column prop="date" label="Date" sortable/>
      <el-table-column prop="name" label="Name" />
      <el-table-column prop="address" label="Address" />
      <el-table-column fixed="right" label="操作">
        <template #default>
          <el-button @click="handleClick"
          >编辑</el-button>
          <el-popconfirm title="Are you sure to delete this?">
            <template #reference>
              <el-button type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div class="demonstration " style="margin: 10px 0;"></div>
    <el-pagination
        v-model:currentPage="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[10, 20, 30, 40]"
        :small="small"
        :disabled="false"
        :background="true"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>
</template>

<script >
// @ is an alias to /src

export default {
  name: 'Home',
  components: {

  },

  methods: {
    handleClick() {
    },
    handleSizeChange() {
    },
    handleCurrentChange() {
    },
  },

  data() {
    return {
      currentPage : 10,
      pageSize : 10,
      small : true,
      total : 100,
      tableData : [
        {
          date: '2016-05-03',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-02',
          name: 'Tom2',
          address: 'No. 189, Grove St, Los Angeles',
        }
      ]
    }
  }
}
</script>




