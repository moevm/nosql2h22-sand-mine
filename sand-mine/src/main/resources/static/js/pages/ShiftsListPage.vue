<template>
  <div class="div-for-table">
    <div class="div_left_top">
      <span class="default-size">Смены Иванова И.И.</span>
    </div>
    <div class="div-search-button">
      <button class="button one_line_buttons with-bot-margin-large" style="width: 250px; float: right" @click="show_search()">Поиск</button>
    </div>
    <Table :data-for-table="dataForTable">
    </Table>
    <ShiftsListSearchModal v-if="show_search_modal"
                           :submit="submit_search"
                           :close="close_search">

    </ShiftsListSearchModal>
    <ShiftsListParamsModal v-if="show_filter_params_modal"
                           :data="filter_params"
                           :close="close_filter_params">

    </ShiftsListParamsModal>
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
import ShiftsListParamsModal from "../components/modal/ShiftsListParamsModal.vue";
import ShiftsListSearchModal from "../components/modal/ShiftsListSearchModal.vue";

export default {
  name: "StaffListPage",
  components: {ShiftsListSearchModal, ShiftsListParamsModal, Modal, Table},
  data() {
    return {
      dataForTable: tableData,
      show_search_modal: false,
      show_filter_params_modal: false,
      filter_params: {date_from: "30.01.2001"}
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
    }
  }
}

let tableData = [
  {
    columnNames: ["Дата смены", "Присутствовал"],
    moreInformationColumn: -1
  }, [], []];

for (let i = 0; i < 100; i++) {
  let id = i;
  tableData[1].push([
    "Иванов И.И.",
    "Да"
  ],);
  tableData[2].push([
    id, -1, -1 //последний айди - айди строки
  ])
}
</script>

<style scoped>

.div_left_top {
  float: left;
  display: inline-block;
  width: 50%;
  margin-top: 15px;
}
</style>