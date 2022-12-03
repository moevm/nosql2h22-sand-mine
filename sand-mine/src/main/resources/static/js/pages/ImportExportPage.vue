<template>
  <div class="background-parent">
    <div class="background">
      <label class="input-file with-bot-margin-medium">
        <input @change="change" type="file" name="file">
        <span class="input-file-btn" v-text="fileName"></span>
      </label>
      <button class="button with-bot-margin-medium" @click="export_">
        Экспортировать данные
      </button>
      <br>
      <button class="button with-bot-margin-medium" @click="import_">
        Импортировать данные
      </button>
      <button class="button" @click="clear">
       Очистить данные
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// import * as fs from "fs/promises"

export default {
  name: "ImportExportPage",
  data() {
    return {
      fileName: "Выберите файл",
      file: null
    }
  },
  methods: {
    import_() {
      console.log(this.fileName)
      let reader = new FileReader();
      reader.readAsText(this.file)

      reader.onload = () => {
        // console.log(reader.result)
        let config = {headers: {'Content-Type': 'application/json'}}
        let importObject = {
          data:reader.result,
          fileName:"asd"
        }
        axios.post("/import",
            JSON.stringify(importObject),
            config
        )
        console.log(importObject)
        // axios.post("/clear","",config)
      }
    },
    download(data, filename, type) {
      var file = new Blob([data], {type: type});
      if (window.navigator.msSaveOrOpenBlob) // IE10+
        window.navigator.msSaveOrOpenBlob(file, filename);
      else { // Others
        var a = document.createElement("a"),
            url = URL.createObjectURL(file);
        a.href = url;
        a.download = filename;
        document.body.appendChild(a);
        a.click();
        setTimeout(function () {
          document.body.removeChild(a);
          window.URL.revokeObjectURL(url);
        }, 0);
      }
    },
    export_() {
      let parser = new DOMParser();
      axios.get("/export").then(result => {
        this.download(result.data.data,"new.xml","text/plain");
        // const link = document.createElement("a");
        // let blob = new Blob([result.data], {type: 'text/plain'})
        // console.log(result.data)
        //
        // // const url = URL.createObjectURL(blob);
        // // this.download(url,"a.xml")
        // // link.href = URL.createObjectURL(blob)
        // // link.download = "new.xml"
        // // link.click()
        // // URL.revokeObjectURL(link.href)
        // fs.writeFile("new.xml", result.data, "utf-8")
      })
    },
    change(event) {
      this.fileName = event.target.files[0].name
      this.file = event.target.files[0]
    },
    clear(){
      axios.post("/clear")
    }
  }
}
</script>

<style scoped>
@import '../../css/button.css';
@import '../../css/main.css';
@import '../../css/margins.css';

.input-file {
  position: relative;
  display: inline-block;
  width: 100%;
}

.input-file-btn {
  background-color: #F9A825;
  border: 3px solid #F9A825;
  border-radius: 10px;
  font-size: 24px;
  display: inline-block;
  padding-left: 10px;
  padding-top: 10px;
  padding-bottom: 30px;
  vertical-align: middle;
  color: black;
  text-align: center;
  line-height: 22px;
  margin: 0;
  width: 100%;
  height: 50px;
  margin-bottom: 10px;
}

.input-file input[type=file] {
  position: absolute;
  z-index: -1;
  opacity: 0;
  display: block;
  width: 0;
  height: 0;
}


/* Hover/active */
.input-file-btn:hover {
  background: rgb(200, 150, 40);
  border-color: rgb(200, 150, 40);
}


</style>