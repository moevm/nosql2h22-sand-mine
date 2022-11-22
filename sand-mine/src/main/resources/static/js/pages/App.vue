<template>
  <div id="app">
    <button class="back-button" @click="back"><span style="font-size: 40px"  class="material-icons">arrow_back</span></button>
    <router-view>
    </router-view>
  </div>
</template>

<script>

export default {
  methods:{
    back(){
      console.log(this.$router)

      this.$router.back();
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