const app = Vue.

    createApp({

        data() {
            return {
                clients: [],
                clientsId: [],
                firstName: "",
                lastName: "",
                email: "",
                id: "",
            }
        },

        created() {

            this.loadData()
            this.loadDataRest()
        },

        methods: {

            loadData() {

                axios.get('/api/clients')
                    .then((response) => {
                        this.clients = response.data
                        console.log(this.clients)
                        this.clientsId = this.clients.map(id => id)
                        // console.log(this.clientsId)
                        // console.log(this.clients);

                    })


            },

            loadDataRest() {

                axios.get('/rest/clients/')
                    .then((response) => {
                        this.clientsRest = response.data._embedded.clients
                        this.clientsIdRest = this.clientsRest.map(id => id._links.self.href)

                    })

            },

            addClient() {
                if (this.firstName != "" && this.lastName != "" && this.email != "") {
                    axios.post('/rest/clients', { //obtiene los datos del nuevo cliente usando AJAX 
                        firstName: this.firstName,
                        lastName: this.lastName,
                        email: this.email
                    })

                        // .then(() => window.location.reload()) //obtiene la dirección de la página actual (URL) y para recargar
                        .then(() => this.loadData())
                        .then(() => {
                            this.firstName = ""
                            this.lastName = ""
                            this.email = ""
                        })
                }
            },

        },

    }).mount('#app')






