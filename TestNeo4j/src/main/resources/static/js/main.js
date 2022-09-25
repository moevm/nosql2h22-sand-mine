let messageApi = Vue.resource("/api/messages{/id}");


Vue.component('message-row',{
    props:['message','messages'],
    template: '<div>' +
        '<i>({{message.id}})</i> {{message.text}}' +
        '<span style=" margin-right:0">' +
        '<input type="button" value="X" v-on:click = "del">' +
        '</span>' +
        '</div>',
    methods: {
        del: function (){
            messageApi.remove({id: this.message.id}).then(result=>{
                if(result.ok){
                    this.messages.splice(this.messages.indexOf(this.message),1)
                }
            })
        }
    }
})

Vue.component('message-list',{
    props:['messages'],
    data: function(){
        return {message:null}
    },
    template:
    '<div>' +
        '<message-form :messages="messages" />' +
        '<message-row v-for="message in messages" :message="message" :key="message.id" :messages="messages"/>' +
        '</div>'

})

Vue.component('message-form',{
    props:['messages'],
    data:function(){
        return{
            text:''
        }
    },
    template:
    '<div>' +
        '<input type="text" placeholder="Write message" v-model="text"/>' +
        '<input type="button" value="Save" v-on:click="save"/>' +
        '</div>',
    methods:{
        save: function(){
            let message = {text:this.text}
            messageApi.save({},message).then(result=>{
                result.json().then(data=>{
                    this.messages.push(data);
                    this.text=''
                })
            })
        }
    }
})

let app = new Vue({
    el: '#app',
    template:
        '<div>' +
            '<message-list :messages="messages"/>'+
        '</div>',
    data: {
        messages: messages
    }
});