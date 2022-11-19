<template>
  <v-app>
    <Modal :title="title" v-if="showModal">
      <template #body>
        Modal Body
      </template>
      <template #footer>
        <button @click="close">Cancel</button>
        <button>Submit</button>
      </template>
    </Modal>
    <div class="div-for-table">
      <Table :dataForTable="dataForTable" :edit="edit" :more="moreInformation">
      </Table>
    </div>
  </v-app>
</template>

<script>
import Table from "components/table/Table.vue"
import Modal from "components/modal/Modal.vue";
export default {
  components: {
    Modal,
    Table
  },
  data() {
    return {
      dataForTable: testData2,
      showModal: false
      ,
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

let testData1 = [
  {
    columnNames: ["ФИО", 'Телефон', 'Должность', 'Зона', 'Дата смены'],
    moreInformationColumn: 0
  }, [], []];


for (let i = 0; i < 100; i++) {
  let id = i;
  testData1[1].push([
    "Иванов И.И.",
    "89144444444",
    'Охрана',
    "I",
    "11.04.2021"
  ],);
  testData1[2].push([
    id, -1, -1, id, -1, -1 //последний айди - айди строки
  ])
}

let testData2 = [
  {
    columnNames: ['Дата', 'Вес, Т', 'Последний редактор', 'Время редактирования'],
    editColumn: 1,
    moreInformationColumn: 2
  }, [], []];

for (let i = 0; i < 100; i++) {
  let id = i;
  testData2[1].push([
    "21.10.2022",
    "0.31",
    'Пушкин А.С.',
    "21.10.2022 20:20"
  ]);
  testData2[2].push([
    -1, -1, id, -1, 100 + id//последний айди - айди строки (например айди всей записи о добыче)
  ])
}

let testData3 = [
  {columnNames: ['Время смены', 'Присутствовал']}, [], []];

for (let i = 0; i < 100; i++) {
  let id = i;
  testData3[1].push([
    "11.04.2022 8:00",
    "Да"
  ]);
  testData3[2].push([
    -1, -1, id
  ])
}

</script>

<style>

</style>