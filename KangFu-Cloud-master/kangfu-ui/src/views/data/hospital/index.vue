<template>
  <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="医院信息" name="first">
      <el-form ref="showForm" :model="showForm" v-loading="loading" label-width="80px">
        <el-form-item label="医院logo">
          <div class="demo-image">
            <div class="block">
              <el-image style="width: 100px; height: 100px" :src="url"></el-image>
            </div>
          </div>
        </el-form-item>
        <el-form-item label="医院名称">
          <h3><span>{{showForm.hospitalName}}</span></h3>
        </el-form-item>
        <el-form-item label="医院等级">
          <h3 v-if="hospitalLevel==0">三甲</h3>
          <h3 v-if="hospitalLevel==1">二甲</h3>
          <h3 v-if="hospitalLevel==2">一甲</h3>
          <h3 v-if="hospitalLevel==3">三乙</h3>
        </el-form-item>
        <el-form-item label="医院类型">
          <h3 v-if="hospitalType==0">综合医院</h3>
          <h3 v-if="hospitalType==1">专科医院</h3>
        </el-form-item>
        <el-form-item label="医院介绍">
          <h3 style="width: 1000px">
          {{showForm.hospitalIntroduce}}
          </h3>
        </el-form-item>
        <el-form-item label="医院图片">
          <div id="app">
            <div class="demo-image__lazy">
              <el-image v-for="url in urls" :key="url" :src="url" lazy></el-image>
            </div>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button>返回</el-button>
          <!-- Form -->
          <el-button type="text" @click="getHospitalInfo">编辑</el-button>
        </el-form-item>
      </el-form>
    </el-tab-pane>




    <!-- 修改医院信息的弹出框 -->
    <el-dialog title="修改医院信息" :visible.sync="dialogFormVisible">
      <el-form ref="form" :model="form" :rules="rules">
        <el-form-item label="医院logo" :label-width="formLabelWidth">
          <div class="text-center">
            <hospitalAvatar :user="hospital" />
          </div>
        </el-form-item>
        <el-form-item label="医院名称" :label-width="formLabelWidth">
          <el-input
            type="text"
            placeholder="请输入内容"
            v-model="form.hospitalName"
            maxlength="20"
            show-word-limit
          ></el-input>
        </el-form-item>
        <el-form-item label="医院等级" :label-width="formLabelWidth">
          <el-select v-model="form.hospitalLevel" placeholder="请选择">
            <el-option
              v-for="dict in dict.type.sys_hospital_level"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="医院类型" :label-width="formLabelWidth">
          <el-select v-model="form.hospitalType" placeholder="请选择">
            <el-option
              v-for="dict in dict.type.sys_hospital_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="医院介绍">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="form.hospitalIntroduce"
            maxlength="255"
            show-word-limit
            clearable
            :autosize="{ minRows: 4, maxRows: 8}"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="医院图片">
          <el-upload
            action="#"
            style="display: inline"
            :file-list="fileList"
            :auto-upload="false"
            :on-change="handleChange">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>



    <!-- 楼层区域 -->
    <el-tab-pane label="楼层区域" name="second">
      楼层区域

    </el-tab-pane>
  </el-tabs>
</template>

<script>
import { getHospitalInfo,showHospitalInfo,updateHospital,updateHospitalImage } from "@/api/system/data/hospital";
import hospitalAvatar from "./hospitalAvatar";

export default {
  name: "Hospital",
  components: { hospitalAvatar },
  dicts: ['sys_hospital_level', 'sys_hospital_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      activeName: 'first',
      // 医院logo
      url: '',
      // 医院介绍图片数组
      urls:[],
      dialogFormVisible: false,
      form: {
        hospitalLogo: [],
        hospitalName: '',
        hospitalLevel: '',
        hospitalType: '',
        hospitalIntroduce: false,
        hospitalImage: []
      },
      formLabelWidth: '120px',
      dialogImageUrl: '',
      dialogVisible: false,
      // 表单校验
      rules: {
        hospitalName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '用户名称长度必须介于 1 和 20 之间', trigger: 'blur' }
        ],
        hospitalIntroduce: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" }
        ]
      },
      // 显示医院数据
      showForm: null,
      hospitalLevel: null,
      hospitalType: null,
      // 医院介绍图片上传文件列表
      fileList:[]
    };
  },
  created(){
    this.showHospitalInfo();
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    /** 显示医院信息 */
    showHospitalInfo(){
      this.loading = true;
      this.dialogFormVisible = false;
      showHospitalInfo().then(response=>{
        this.showForm = response.hospital;
        this.hospitalLevel = response.hospital.hospitalLevel;
        this.hospitalType = response.hospital.hospitalType;
        this.url = response.hospital.hospitalLogo;
        this.urls = response.hospitalImageArray;
        this.loading = false;
      });

    },
    /** 获取医院信息 */
    getHospitalInfo(){
      this.dialogFormVisible = true;
      getHospitalInfo().then(response=>{
        this.form=response.hospital;
      });

    },
    /** 提交按钮 */
    submitForm(){
      this.$refs["form"].validate(valid => {
        if (valid) {
          let fd=new FormData();
          this.fileList.forEach(item=>{
            fd.append("files",item.raw);
          });
          /** 上传医院介绍图片数组 */
          updateHospitalImage(fd).then(response=>{

          });
          /** 更新医院基本信息 */
          updateHospital(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.dialogFormVisible = false;
            this.showHospitalInfo();
          });
        }
      });
    },
    /** 获取文件列表 */
    handleChange(file,fileList){
      this.fileList=fileList;
    }



  }
};
</script>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
