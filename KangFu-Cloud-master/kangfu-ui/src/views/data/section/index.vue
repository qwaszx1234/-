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
          <el-button type="primary" @click="onSubmit">编辑</el-button>
        </el-form-item>
      </el-form>

    </el-tab-pane>
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
        fits: ['fill'],
        url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
      };
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      },
      onSubmit() {
        console.log('submit!');
      }
    }
  };
</script>
