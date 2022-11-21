<template>
  <div class="div-for-table">
    <div class="button-div">
      <div class="div-search-button">
        <button class="button btn" style="float:right;width:250px" @click="showSearchModal()">Поиск</button>
        <div>
          <button class="button_params" style="float:right" @click="showModal_params=true">
            Текущие параметры поиска
          </button>
        </div>
      </div>
      <div style="float: left; margin-top:40px">
        <button class="button btn" @click="showAddModal()">Добавить</button>
      </div>
    </div>
    <Table :dataForTable="data_for_table" :edit="edit" :more="moreInformation"></Table>
  </div>
  <ModalSearch :options_editor="options_editor"
               :options_zone="options_zone"
               :value_editor="value_editor"
               :value_zone="value_zone"
               :show_modal="showModal_search"
               :title="title_search"
               :submit="submit_search"
               :close="close_search"
  />
  <ModalParams :close="close_params"
               :show_modal="showModal_params"
               :data="params"
  />
  <!--  если data_for_edit null , то происходит добавление новой записи. Иначе - редактирование старой.-->
  <ModalAddProduction :close="close_add"
                      :submit_add="submit_add"
                      :show_modal="showModal_add"
                      :title="title_add"
                      :options_zone="options_zone"
                      :data_for_edit="data_for_edit"
                      :submit_edit="submit_edit_production"
  />

</template>

<script>
import Table from "../components/table/Table.vue";
import ModalSearch from "./parts/ModalSearch.vue"
import ModalParams from "./parts/ModalParams.vue";
import ModalAddProduction from "./parts/ModalAddProduction.vue";

export default {
  components: {
    Table,
    ModalSearch,
    ModalParams,
    ModalAddProduction
  },
  data() {
    return {
      data_for_table: test_data,
      showModal_search: false,
      showModal_params: false,
      showModal_add: false,
      title_search: "Поиск",
      title_add: "Добавление",
      value_editor: [],
      value_zone: [],
      options_editor: [
        "Kirill",
        "Alex",
        "Mihail",
        "Vitaliy",
        "Roman"
      ],
      options_zone: [
        "Zone1",
        "Zone2",
        "Zone3",
        "Zone4",
        "Zone5"
      ],
      params: {empty: true},
      data_for_edit: null
    }
  },
  methods: {
    close_search() {
      this.showModal_search = false;
    },
    close_params() {
      this.showModal_params = false;
    },
    close_add() {
      this.showModal_add = false;
      this.data_for_edit = null;
    },
    edit(id) {//при нажатии кнопки редактирования в Table сюда придет id
      // console.log('edit:id=', id)
      let production = this.get_production_by_id(id)
      if (!production) {
        console.log('ERROR! PRODUCTION IS NULL IN PRODUCTION PAGE! HELP ME!')
      }
      this.data_for_edit = production
      this.data_for_edit['id'] = id
      this.showModal_add = true
    },
    submit_edit_production(data) {//после редактирования информации о добыче сюда придет отредактированная информация
      console.log(data)
    },
    moreInformation(id) {//при нажатии кнопки "Подробнее" сюда придет id, можно открыть экран "Аккаунт работника"
      console.log('more:id=', id)
    },
    showAddModal() {
      this.showModal_add = true;
    },
    showSearchModal() {
      this.showModal_search = true
    },
    submit_search(data) {//сюда приходят данные из модалки с поиском
      this.params = data;
      console.log(this.params)
      this.add_params();
    },
    submit_add(data) {//сюда приходят данные из модалки с добавлением
      console.log(data)
      /*
      * записываем в бд, после получения ответа отображаем таблицу заново
      * */
    },
    add_params() {//добавляются параметры для отображения в "Текущих параметрах поиска"
      let c = 0
      for (let i in this.params) {
        if (this.params[i] != null) {
          if (typeof (this.params[i]) === 'string') {
            if (this.params[i].length !== 0) {
              c += 1;
            }
          } else {
            c += 1;
          }
        }
      }
      if (c === 1 && this.params['zones'] && this.params['zones'].length === 0) {
        c = 0;
      }
      this.params['empty'] = c === 0;
    },
    get_production_by_id(id) {//вместо записи из таблицы потом надо запись из бд выдавать
      for (let p_ind = 0; p_ind < this.data_for_table[1].length; p_ind++) {
        if (this.data_for_table[2][p_ind][this.data_for_table[2][p_ind].length - 1] === id) {
          return this.data_for_table[1][p_ind];
        }
      }
      return null;
    }
  }
}

let test_title = "Заголовок"

let test_data = [
  {
    columnNames: ['Дата', 'Вес, Т', 'Последний редактор', 'Время редактирования'],
    editColumn: 1,
    moreInformationColumn: 3
  }, [], []];

for (let i = 0; i < 100; i++) {
  let id = i;
  test_data[1].push([
    "21.10.2022",
    "0.31",
    "Zone2",
    'Пушкин А.С.',
    "21.10.2022 20:20"
  ]);
  test_data[2].push([
    -1, -1, id, id, -1, 100 + id//последний айди - айди строки (например айди всей записи о добыче)
  ])
}
</script>

<style>
.btn {
  padding-bottom: 40px;
}

.button_params {
  background: none;
  border: none;
  color: rgb(200, 100, 40);
  font-size: 12px;
  width: 300px;
}

.button_params:hover {
  color: #F9C025;
}

.div-search-button {
  float: right;
  display: inline-block;
  width: 50%;
  margin-top: 15px;
}

label {
  font-size: 16px;
}

.multiselect__input {
  color: black;
}

.multiselect-single-label {
  color: black;
}


.is-selected {
  background: orange !important;
}

.multiselect {
  border-radius: 20px !important;
  border: 3px solid orange !important;;
}

.is-open, .is-active {
  box-shadow: none !important;
}

.multiselect-search {
  background: none !important;
  border: none !important;
}


</style>

<style src="@vueform/multiselect/themes/default.css">

</style>