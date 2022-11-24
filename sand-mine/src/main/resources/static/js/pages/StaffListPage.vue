<template>
  <div class="div-for-table">
    <div class="div-search-button">
      <button class="button one_line_buttons with-bot-margin-large" style="width: 250px; float: right" @click="show_search()">Поиск</button>
    </div>
    <Table :data-for-table="dataForTable" :more="more_information">
    </Table>
    <StaffListSearchModal v-if="show_search_modal"
                          :options_zones="options_zone"
                          :title="title"
                          :submit="submit_search"
                          :close="close_search">

    </StaffListSearchModal>
    <StaffListParamsModal v-if="show_filter_params_modal"
                          :data="filter_params"
                          :close="close_filter_params">
    </StaffListParamsModal>
    <div class="div-search-button">
      <button class="button" style="float: right" @click="show_filter_params()">Текущие параметры
        поиска
      </button>
    </div>
  </div>
</template>

<script>
import Table from "../components/table/Table.vue";
import Modal from "../components/modal/Modal.vue";
import StaffListSearchModal from "../components/modal/StaffListSearchModal.vue";
import StaffListParamsModal from "../components/modal/StaffListParamsModal.vue";

export default {
  name: "StaffListPage",
  components: {StaffListParamsModal, StaffListSearchModal, Modal, Table},
  data() {
    return {
      dataForTable: tableData,
      title: "Поиск",
      options_zone: [
        "Zone1",
        "Zone2",
        "Zone3",
        "Zone4"
      ],
      show_search_modal: false,
      show_filter_params_modal: false,
      filter_params: {full_name: "Иванов Иван Иванович", zones: ["Всеволожск", "Колпино"]}
    }
  },
  methods: {
    show_search() {
      this.show_search_modal = true
    },
    close_search() {
      this.show_search_modal = false
    },
    submit_search() {
    },
    show_filter_params() {
      this.show_filter_params_modal = true
    },
    close_filter_params() {
      this.show_filter_params_modal = false
    },
    more_information() {

    }
  }
}

let tableData = [
  {
    columnNames: ["ФИО", 'Телефон', 'Должность', "Доступ в зоны"],
    moreInformationColumn: 0
  }, [], []];

for (let i = 0; i < 100; i++) {
  let id = i;
  tableData[1].push([
    "Иванов И.И.",
    "89144444444",
    'Охрана',
    "11.04.2021"
  ],);
  tableData[2].push([
    id, -1, -1, -1, -1, -1 //последний айди - айди строки
  ])
}
</script>

<style scoped>
</style>