<template>

    <v-data-table
        :headers="headers"
        :items="getLetcure"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'GetLetcureView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            getLetcure : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/getLetcures'))

            temp.data._embedded.getLetcures.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.getLetcure = temp.data._embedded.getLetcures;
        },
        methods: {
        }
    }
</script>

