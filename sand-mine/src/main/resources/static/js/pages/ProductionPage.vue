<template>
  <div class="div-for-table">
    <div class="button-div">
      <button class="button btn" onclick = "console.log('click')">asd</button>
    </div>
    <Table :dataForTable="dataForTable" :edit="edit" :more="moreInformation"></Table>
  </div>
</template>

<script>
import Modal from "../components/modal/Modal.vue";
import Table from "../components/table/Table.vue";

export default {
  components: {
    Modal,
    Table
  },
  data() {
    return {
      dataForTable: testData,
      showModal: false,
      title: test_title
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
    }
  }
}

let test_title = "Заголовок"

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
  }
  .button-div{
    width: 100%;
  }
</style>