<template>
  <div class="background-parent">
    <button class="back-button" @click="navigateBack"></button>
    <div class="div-for-table">
      <div class="div-search-button">
        <button class="button one_line_buttons with-bot-margin-large" style="width: 250px; float: right" @click="showSearch()">Поиск</button>
      </div>
      <Table :dataForTable="dataForTable" :more="navigateStaffAccount"></Table>
      <StaffShiftsSearchModal v-if="showSearchModal" :data="searchData" :options_zones="optionsZone" :submit="submitSearch" :close="closeSearch"></StaffShiftsSearchModal>
      <StaffShiftsParamsModal v-if="showSearchParamsModal" :data="searchData" :close="closeSearchParams"></StaffShiftsParamsModal>
      <div class="div-search-button">
        <button class="button" style="float: right"  @click="showSearchParams()">Текущие параметры поиска</button>
      </div>
    </div>
  </div>
</template>

<script>
  import Table from "../components/table/Table.vue";
  import Modal from "../components/modal/Modal.vue";
  import StaffShiftsParamsModal from "../components/modal/StaffShiftsParamsModal.vue";
  import StaffShiftsSearchModal from "../components/modal/StaffShiftsSearchModal.vue";

  export default {
    name: "StaffShiftspage",
    components: {
        StaffShiftsSearchModal,
        StaffShiftsParamsModal,
        Modal,
        Table
    },
    data() {
      return {
        dataForTable: staffShifts,
        showSearchModal: false,
        showSearchParamsModal: false,
        optionsZone: [
          "Zone1",
          "Zone2",
          "Zone3",
          "Zone4"
        ],
        searchData: {
          fullName: null,
          phone: null,
          role: null,
          zones: null,
          dateStart: null,
          dateEnd: null,
        }
      }
    },
    methods: {
      navigateBack() {
      },
      navigateStaffAccount(id) {
        console.log(id)
      },
      showSearch() {
        this.showSearchModal = true
      },
      closeSearch() {
        this.showSearchModal = false
      },
      submitSearch(searchData) {
        console.log(searchData)
        this.searchData = searchData
      },
      showSearchParams() {
        this.showSearchParamsModal = true
      },
      closeSearchParams() {
        this.showSearchParamsModal = false
      },
    }
  }
  let staffShifts = [
  {columnNames : ['ФИО','Телефон','Должность', 'Зона', 'Дата смены'],
    editColumn: -1,
    moreInformationColumn: 0
  }, [],[]];

  for (let i =0;i<100;i++){
  let id = i;
  staffShifts[1].push([
    "Иванов И.И.",
    "89144444444",
    'Охрана',
    "Зона_1",
    "11.04.2021"
  ],);
  staffShifts[2].push([
      id,-1,-1,-1,-1,-1 //последний айди - айди строки
  ])
}
</script>

<style scoped>
@import "../../css/text.css";
@import "../../css/main.css";
</style>
