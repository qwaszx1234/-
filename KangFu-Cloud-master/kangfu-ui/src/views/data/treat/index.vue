<template>
  <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="医院信息" name="first">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="医院logo">
          <div class="demo-image">
            <div class="block" v-for="fit in fits" :key="fit">
              <el-image
                style="width: 100px; height: 100px" :src="url" :fit="fit"></el-image>
            </div>
          </div>
        </el-form-item>
        <el-form-item label="医院名称">
          <h3>西藏山南人民医院</h3>
        </el-form-item>
        <el-form-item label="医院等级">
          <h3>三甲</h3>
        </el-form-item>
        <el-form-item label="医院类型">
          <h3>综合医院</h3>
        </el-form-item>


        <el-form-item label="医院介绍">
          <h3 style="width: 1000px">
          我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍
          我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍
          我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍
          我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍
          我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍
          我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍
          我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍我是医院介绍
          </h3>
        </el-form-item>
        <el-form-item label="医院图片">
          <div class="demo-image">
            <div class="block" v-for="fit in fits" :key="fit">
              <el-image
                style="width: 100px; height: 100px" :src="url" :fit="fit"></el-image>
            </div>
          </div>
        </el-form-item>


        <el-form-item>
          <el-button>返回</el-button>
          <!-- Form -->
          <el-button type="text" @click="dialogFormVisible = true">编辑</el-button>
        </el-form-item>
      </el-form>

    </el-tab-pane>
    <!-- 修改医院信息的弹出框 -->
    <el-dialog title="修改医院信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">


        <el-form-item label="活动名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动区域" :label-width="formLabelWidth">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        --
        <el-form-item label="医院logo" :label-width="formLabelWidth">
          <div class="demo-image">
            <div class="block" v-for="fit in form.hospitalLogo" :key="fit">
              <el-image
                style="width: 100px; height: 100px" :src="url" :fit="fit"></el-image>
            </div>
          </div>
        </el-form-item>
        <el-form-item label="医院名称" :label-width="formLabelWidth">
          <el-input v-model="form.hospitalName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="医院等级" :label-width="formLabelWidth">
          <h3>三甲</h3>
          <template>
            <el-select v-model="form.hospitalLevel" placeholder="请选择">
              <el-option v-if="0" :key="form.hospitalLevel" :label="hospitalLevel" :value="form.hospitalLevel">
              </el-option>
            </el-select>
          </template>
        </el-form-item>
        <el-form-item label="医院类型" :label-width="formLabelWidth">
          <h3>综合医院</h3>
        </el-form-item>


        <el-form-item label="医院介绍">

        </el-form-item>
        <el-form-item label="医院图片">
          <div class="demo-image">
            <div class="block" v-for="fit in fits" :key="fit">
              <el-image
                style="width: 100px; height: 100px" :src="url" :fit="fit"></el-image>
            </div>
          </div>
        </el-form-item>
        --


      </el-form>


      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>



    <!-- 楼层区域 -->
    <el-tab-pane label="楼层区域" name="second">
      楼层区域

    </el-tab-pane>
  </el-tabs>
</template>
<script>
  export default {
    data() {
      return {
        activeName: 'first',
        fits: ['fill'],
        url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        dialogFormVisible: false,
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        formLabelWidth: '120px'
      };
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      }
    }
  };
</script>
