<template>
  <div id="app">
    <button class="back-button" @click="back" v-if="check_visibility()"><span style="font-size: 40px"  class="material-icons">arrow_back</span></button>
    <router-view>
    </router-view>
  </div>
</template>

<script>
import { AUTHORIZATION_PAGE_NAME } from '../router/component_names'

export default {
  methods:{
    back(){
      console.log(this.$router)

      this.$router.back();
    },
    check_visibility() {
      return this.$route.name != AUTHORIZATION_PAGE_NAME
    }
  }
}

</script>

<style>
  .back-button{
    position: fixed;
    background: none;
    border: none;
    top:4%;
    left:19%;
    width:3%;
    z-index: 1;
  }
  .back-button:hover{
    color:rgb(255,150,2);
  }
</style>