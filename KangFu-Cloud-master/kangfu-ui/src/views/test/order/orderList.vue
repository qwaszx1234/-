<template>
  <div class="app-container">
    <!-- 用户信息 -->
    <h4 class="form-header h4">用户信息</h4>
    <el-form ref="userForm" :model="userForm" label-width="80px">
      <el-row>
        <el-col :span="8" :offset="2">
          <el-form-item label="用户编号" prop="userId">
            <el-input v-model="userForm.userId" disabled />
          </el-form-item>
        </el-col>
        <el-col :span="8" :offset="2">
          <el-form-item label="用户昵称" prop="nickName">
            <el-input v-model="userForm.nickName" disabled />
          </el-form-item>
        </el-col>
        <el-col :span="8" :offset="2">
          <el-form-item label="登录账号" prop="phonenumber">
            <el-input  v-model="userForm.userName" disabled />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <!-- 新增按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['test:user:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['test:order:update']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['test:order:delete']"
        >删除</el-button>
      </el-col>
      <right-toolbar @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <!-- 订单信息 -->
    <h4 class="form-header h4">订单信息</h4>
    <el-table v-loading="loading" :row-key="getRowKey" @row-click="clickRow" ref="table" @selection-change="handleSelectionChange" :data="orders.slice((pageNum-1)*pageSize,pageNum*pageSize)">
    <el-table-column label="序号" type="index" align="center">
      <template slot-scope="scope">
        <span>{{(pageNum - 1) * pageSize + scope.$index + 1}}</span>
      </template>
    </el-table-column>
    <el-table-column type="selection" :reserve-selection="true" width="50" align="center" />
    <el-table-column label="订单编号" align="center" key="orderId" prop="orderId" v-if="columns[0].visible" :show-overflow-tooltip="true" />
    <el-table-column label="订单内容" align="center" key="content" prop="content" v-if="columns[1].visible" :show-overflow-tooltip="true" />
    <el-table-column label="用户编号" align="center" key="userId" prop="userId" v-if="columns[2].visible" :show-overflow-tooltip="true" />
    <el-table-column label="用户昵称" align="center" key="nickName" prop="nickName" v-if="columns[3].visible" :show-overflow-tooltip="true" >
      <template>
        <span>{{ this.nickName }}</span>
      </template>
    </el-table-column>
    <el-table-column label="创建时间" align="center" key="createtime" prop="createtime" v-if="columns[4].visible" :show-overflow-tooltip="true" width="160">
      <template slot-scope="scope">
        <span>{{ parseTime(scope.row.createTime) }}</span>
      </template>
    </el-table-column>
    <!-- 操作列 -->
    <el-table-column
      label="操作"
      align="center"
      width="160"
      class-name="small-padding fixed-width"
    >
    <template slot-scope="scope">
      <!-- 修改按钮 -->
      <el-button
        size="mini"
        type="text"
        icon="el-icon-edit"
        @click="handleUpdate(scope.row)"
        v-hasPermi="['test:order:update']"
      >修改</el-button>
      <!-- 删除按钮 -->
      <el-button
        size="mini"
        type="text"
        icon="el-icon-delete"
        @click="handleDelete(scope.row)"
        v-hasPermi="['test:order:delete']"
      >删除</el-button>
    </template>
    </el-table-column>
    </el-table>
    <!-- 页码 -->
    <pagination v-show="total>0" :total="total" :page.sync="pageNum" :limit.sync="pageSize" />

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="insertAndUpdateForm" :model="insertAndUpdateForm" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户编号" prop="userId">
              <el-input v-model="insertAndUpdateForm.userId" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
        <el-col :span="12">
          <el-form-item v-if="insertAndUpdateForm.orderId != undefined" label="订单编号" prop="orderId">
            <el-input v-model="insertAndUpdateForm.orderId" maxlength="30" />
          </el-form-item>
        </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item v-if="insertAndUpdateForm.content != undefined" label="订单内容" prop="content">
              <el-input v-model="insertAndUpdateForm.content" type="textarea"></el-input>
            </el-form-item>
            <el-form-item v-if="insertAndUpdateForm.content == undefined" label="订单内容" prop="content">
              <el-input v-model="insertAndUpdateForm.content" type="textarea" placeholder="请输入订单内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      <!-- 确定和取消操作 -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitInsertAndUpdateForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { getOrderList, getUserByUserId, getOrderByOrderId, updateOrder, deleteOrder, insertOrder } from "@/api/test/order";

export default {
  name: "Order",
  dicts: ['sys_normal_disable', 'sys_user_sex'],
  data() {
    return {
       // 遮罩层
      loading: true,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 分页信息
      total: 0,
      pageNum: 1,
      pageSize: 10,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 用户表格
      userForm: {},
      // userId
      userId: undefined,
      // 用户昵称
      nickName: undefined,
      // 订单表格数据
      orders: null,
      // orderId
      orderId: undefined,
      // 选中订单编号数组
      orderIds:[],
      // 新增或修改按钮的弹出框
      insertAndUpdateForm: {},
      // 列信息
      columns: [
        { key: 0, label: `订单编号`, visible: true },
        { key: 1, label: `订单内容`, visible: true },
        { key: 2, label: `用户编号`, visible: true },
        { key: 3, label: `用户昵称`, visible: true },
        { key: 4, label: `创建时间`, visible: true }
      ],
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "userId不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "订单内容不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 查询用户信息及其订单列表信息
    getList() {
      const userId = this.$route.params && this.$route.params.userId;
      this.userId = userId;
      if (userId) {
        this.loading = true;
        // 根据userId查询用户信息
        getUserByUserId(userId).then((response) => {
          this.userForm = response.user;
          this.nickName = response.user.nickName;
        });
        // 根据userId查询订单列表
        getOrderList(userId).then((response) => {
          this.orders = response.orders;
          this.total = this.orders.length;
          this.loading = false;
        });
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加订单";
      const userId = this.$route.params && this.$route.params.userId;
      this.insertAndUpdateForm.userId = userId;
    },
    /** 修改按钮操作，先获取单个订单对象信息，显示原有信息，修改填充数据后，再提交 */
    handleUpdate(row) {
      this.reset();
      this.open = true;
      this.title = "修改订单";
      const orderId = row.orderId || this.orderIds;
      getOrderByOrderId(orderId).then(response => {
        this.insertAndUpdateForm = response.order;
        this.userId = response.order.userId;
        this.orderId = response.order.orderId;
      });
    },
    /** 删除按钮操作 */
     handleDelete(row) {
       const orderIds = row.orderId || this.orderIds;
       if(orderIds != null && orderIds !=''){
        this.$modal.confirm('是否确认删除订单编号为"' + orderIds + '"的数据项？').then(function() {
           return deleteOrder(orderIds);
         }).then(() => {
          this.getList();
          this.orderIds = null;
          this.$modal.msgSuccess("删除成功");
          this.orderIds=[];
         }).catch(() => {});
       }else{
        this.$modal.msgError("请勾选需要删除的数据");
       }

     },
    /** 提交按钮 */
    submitInsertAndUpdateForm: function() {
     this.$refs["insertAndUpdateForm"].validate(valid => {
       if (valid) {
         if (this.insertAndUpdateForm.orderId != undefined) {
           updateOrder(this.insertAndUpdateForm).then(response => {
              if(response.rows>0){
                this.$modal.msgSuccess("修改成功");
              }else{
                this.$modal.msgError("修改失败");
              }
              this.orderIds=[];
              this.open = false;
              this.getList();
           });
         } else {
           insertOrder(this.insertAndUpdateForm).then(response => {
              if(response.rows>0){
                this.$modal.msgSuccess("新增成功");
              }else{
                this.$modal.msgError("新增失败");
              }
              this.orderIds=[];
              this.open = false;
              this.getList();
           });
         }
       }
     });
   },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.insertAndUpdateForm = {
        orderId: undefined,
        content: undefined,
        userId: undefined
      };
      this.resetForm("insertAndUpdateForm");
    },
   // 多选框选中数据
   handleSelectionChange(selection) {
     this.orderIds = selection.map((item) => item.orderId);
     this.single = selection.length != 1;
     this.multiple = !selection.length;
   }
  },
  // 保存选中的数据编号
  getRowKey(row) {
    return row.orderId;
  },
  /** 单击选中行数据 */
  clickRow(row) {
    this.$refs.table.toggleRowSelection(row,false);
  }

};
</script>
