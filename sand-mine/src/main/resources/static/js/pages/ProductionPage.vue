<template>
  <div class="div-for-table">
    <div class="button-div">
      <div class="div-search-button" >
        <button class="button btn" style="float:right"  @click = "search()">Поиск</button>
        <div>
          <span style="float:right">
            Текущие параметры поиска: нет параметров
          </span>
        </div>
      </div>
      <div style="float: left; margin-top:40px">
        <button class="button btn" @click="add()">Добавить</button>
      </div>
    </div>
    <Table :dataForTable="dataForTable" :edit="edit" :more="moreInformation"></Table>
  </div>
  <Modal :title="title_search" v-if="showModal">
    <template #body>
      <div>
        <label for="date_from">Дата от</label>
        <br>
        <input class="input" id='date_from' name="date_from" type="date" >

        <label for="date_from">Дата, до</label>
        <br>
        <input class="input" id='date_to'  name="date_to" type="date" >

        <label for="weight_from">Вест, т, от</label>
        <br>
        <input class="input" id="weight_from" name="weight_from" type="number">

        <label for="weight_to">Вес, т, до</label>
        <br>
        <input class="input" id="weight_to" name="weight_to" type="number">

        <label for="last_editor">Последний редактор</label>
        <br>
        <Multiselect
            id="last_editor"
            name="last_editor"
            v-model="value_zone"
            :close-on-select="false"
            :options=options_editor
            :multiple="true"
            :clear-on-select="false"
            :preserve-search="false"
            placeholder="Последний редактор"
            :preselect-first="true"/>


        <label for="date_edit">Дата редактирования</label>
        <br>
        <input class="input" id='date_edit'  name="date_edit" type="date" >

        <label for="zones">Зоны</label>
        <br>
<!--        <input class="input" id="zones" name="zones" type="text">-->
        <Multiselect
            name="zones"
            id="zones"
            mode="multiple"
            v-model="value_editor"
            :close-on-select="false"
            :options=options_zone
            :multiple="true"
            :clear-on-select="false"
            :preserve-search="false"
            placeholder="Зоны"
            :preselect-first="true"/>

      </div>
    </template>
    <template #footer>
      <button class="button" @click="close">Cancel</button>
      <button class="button" type="submit" >Submit</button>
    </template>
  </Modal>
</template>

<script>
import Modal from "../components/modal/Modal.vue";
import Table from "../components/table/Table.vue";
import Multiselect from "@vueform/multiselect";

export default {
  components: {
    Modal,
    Table,
    Multiselect
  },
  data() {
    return {
      dataForTable: testData,
      showModal: false,
      title_search: "Поиск",
      value_editor: [],
      value_zone: [],
      options_editor: [
        "Kirill",
          "Alex",
          "Mihail",
          "Vitaliy",
          "Roman"
      ],
      options_zone:[
        "Zone1",
        "Zone2",
        "Zone3",
        "Zone4",
        "Zone5"
      ]
    }
  },
  methods: {
    close() {
      this.showModal = false
    },
    edit(id) {
      this.showModal = true
      console.log('edit:id=', id)
    },
    moreInformation(id) {
      console.log('more:id=', id)
    },
    add(){

    },
    search(){
      this.showModal=true
    }
  }
}

let test_title = "Заголовок"

let t = [ {
  columnNames: ['Дата', 'Вес'],
  editColumn: 1,
}, [], []];

for (let i =0;i<222;i++){
  t[1].push(['aasd','bsdfdsfd']);
  t[2].push([1,2])
}

let testData = [
  {
    columnNames: ['Дата', 'Вес, Т', 'Последний редактор', 'Время редактирования'],
    editColumn: 1,
    moreInformationColumn: 2
  }, [], []];

for (let i = 0; i < 100; i++) {
  let id = i;
  testData[1].push([
    "21.10.2022",
    "0.31",
    'Пушкин А.С.',
    "21.10.2022 20:20"
  ]);
  testData[2].push([
    -1, -1, id, -1, 100 + id//последний айди - айди строки (например айди всей записи о добыче)
  ])
}
</script>

<style>
  .btn{
    width:200px;
    padding-bottom: 40px;
  }
  .button:hover{
    background: #F9C025;
  }

  .button-div{
    width: 100%;
  }

  .div-search-button{
    float: right;
    display: inline-block;
    width: 50%;
    margin-top: 15px;
  }
  .input{
    width: 100%;
    height:30px
  }
  label{
    font-size: 16px;
  }

</style>

<style  src="@vueform/multiselect/themes/default.css">

</style>