<template>
    <div>
        <el-col class="accountInfo" :span="12">
            <h1>Account Information</h1>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">Nickname:</el-col>
                <el-col :span="16">
                    <el-input v-model="nickname" placeholder="Input your nickname"></el-input>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">First name::</el-col>
                <el-col :span="16">
                    <el-input v-model="first_name" placeholder="Input your first name"></el-input>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">Last name:</el-col>
                <el-col :span="16">
                    <el-input v-model="last_name" placeholder="Input your last name"></el-input>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">Phone number:</el-col>
                <el-col :span="16">
                    <el-input v-model="Phone_number" placeholder="Input your phone number"></el-input>
                </el-col>
            </el-row>
        </el-col>
        <el-col class="accountPic" :span="12">
            <p>img</p>
        </el-col>
    </div>
</template>

<script>
    export default {
        name: "accountSet"
    }
</script>

<style scoped>
    .allWidth {
        margin: 20px;
    }

</style>