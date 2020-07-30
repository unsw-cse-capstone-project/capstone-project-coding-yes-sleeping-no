<template>
    <div>
        <el-col class="accountInfo" :span="12">
            <h1 class="press">Account Information</h1>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">Username:</el-col>
                <el-col :span="16">
                    <el-input :placeholder="user.user_name" disabled></el-input>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">Email address:</el-col>
                <el-col :span="16">
                    <el-input :placeholder="user.email" disabled></el-input>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">User balance</el-col>
                <el-col :span="16">
                    <el-input :placeholder="user.user_balance" disabled></el-input>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">Password:</el-col>
                <el-col :span="16">
                    <router-link to="/resetPwd">
                        <el-button style="width: 313px" type="primary" icon="el-icon-key">Reset password</el-button>
                    </router-link>

                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">First name:</el-col>
                <el-col :span="16">
                    <el-input v-model="user.first_name" placeholder="Input your first name"></el-input>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">Last name:</el-col>
                <el-col :span="16">
                    <el-input v-model="user.last_name" placeholder="Input your last name"></el-input>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">Phone number:</el-col>
                <el-col :span="16">
                    <el-input v-model="user.phone" placeholder="Input your phone number"></el-input>
                </el-col>
            </el-row>

            <h1 class="press">Mail Address</h1>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">Address line 1:</el-col>
                <el-col :span="16">
                    <el-input v-model="user.address_1" placeholder="Street address, P.O. box, company name, c\o"></el-input>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">Address line 2:</el-col>
                <el-col :span="16">
                    <el-input v-model="user.address_2" placeholder="Apartment, suite, unit, building, floor, etc."></el-input>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">City:</el-col>
                <el-col :span="16">
                    <el-input v-model="user.city" placeholder="Input your city"></el-input>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">State/Province:</el-col>
                <el-col :span="16">
                    <el-select style="width:314px" v-model="user.state" placeholder="Select">
                        <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8" class="abHeight">Zip Code:</el-col>
                <el-col :span="16">
                    <el-input v-model="user.postcode" placeholder="Input your Zip code"></el-input>
                </el-col>
            </el-row>
            <el-row class="allWidth">
                <el-col :span="8"  >
                    <button class="push_button blue" @click="edit">Save</button>
                </el-col>
                <el-col :span="16" class="abHeight" style="text-align: right">
                    <router-link to="/">
                        <button class="push_button red">Cancel</button>
                    </router-link>
                </el-col>
            </el-row>
        </el-col>
        <el-col id="profile_pic" :span="12">
            <h1>Avatar</h1>
            <el-upload
                    class="avatar-uploader"
                    action="#"
                    :show-file-list="false"
                    :http-request="httpRequest">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
        </el-col>
    </div>
</template>

<script>
    export default {
        name: 'accountSet',
        data() {
            return {
                options: [{
                    value: 'ACT',
                    label: 'ACT'
                }, {
                    value: 'NSW',
                    label: 'NSW'
                }, {
                    value: 'NT',
                    label: 'NT'
                }, {
                    value: 'QLD',
                    label: 'QLD'
                }, {
                    value: 'SA',
                    label: 'SA'
                }, {
                    value: 'TAS',
                    label: 'TAS'
                }, {
                    value: 'VIC',
                    label: 'VIC'
                }, {
                    value: 'WA',
                    label: 'WA'
                }, {
                    value: 'JBT',
                    label: 'JBT'
                }],
                user: {},
                imageUrl: '',
            }
        },
        methods: {
            edit() {
                this.$http.post("/user/updateUserInfo", this.user).then(
                    res=>{
                        if(res.state){
                            this.$message({
                                message: res.msg,
                                type: 'success'
                            });
                            localStorage.setItem("user", JSON.stringify(res));
                            location.reload();
                        }
                        else {
                            this.$message({
                                message: res.msg,
                                type: 'fail'
                            });
                        }
                    })
            },
            //这个file参数 也就是文件信息
            getBase64(file) {
                console.log(file);
                return new Promise((resolve, reject) => {
                    let reader = new FileReader();
                    let fileResult = "";
                    reader.readAsDataURL(file);
                    //开始转
                    reader.onload = function() {
                        fileResult = reader.result;
                    };
                    //转 失败
                    reader.onerror = function(error) {
                        reject(error);
                    };
                    //转 结束  咱就 resolve 出去
                    reader.onloadend = function() {
                        resolve(fileResult);
                    };
                });
            },
            httpRequest(data) {　　// 没事儿就打印data看看呗
                //这是限制上传文件类型
                console.log(data);
                const isJPG = data.file.type === "image/jpeg";
                const isPNG = data.file.type === "image/png";
                const isGIF = data.file.type === "image/gif";
                const isLt2M = data.file.size / 1024 / 1024 < 2;

                if (!isJPG && !isPNG && !isGIF) {
                    this.$message.error("The format of profile picture must be JPG or PNG or GIF!");
                } else if (!isLt2M) {
                    this.$message.error("The size of profile picture must be less than 2MB!");
                } else {
                    // 转base64
                    this.getBase64(data.file).then(resBase64 => {
                        this.user.avatar = resBase64;　//直接拿到base64信息
                        console.log(resBase64);
                    })
                    this.imageUrl = URL.createObjectURL(data.file);
                    this.$message.success('File upload successfully!');
                }
            }
        },
        created() {
            let userString = localStorage.getItem("user");
            if(userString){
                this.user =  JSON.parse(userString).user;
                this.imageUrl = this.getPic(this.user.avatar);
                // let len = this.user.avatar.split('/').length - 1;
                // const file = this.user.avatar.split('/')[len];
                // this.imageUrl = require('/Users/edgar/Desktop/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/' + file);
            } else{
                alert("You have not logged in yet, click OK to jump to the login page!");
                location.href ="/";
            }
        }
    }
</script>

<style scoped>
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
    .allWidth {
        margin: 20px;
        text-align: left;
        display: flex;
        align-items: center;

    }

    .press { color: transparent;
        background-color : black;
        text-shadow : rgba(255,255,255,0.5) 0 5px 6px, rgba(255,255,255,0.2) 1px 3px 3px;
        -webkit-background-clip : text;
        margin: 20px;
        text-align: left;}

    .push_button {
        position: relative;
        width:100px;
        height:40px;
        text-align:center;
        color:#FFF;
        text-decoration:none;
        line-height:43px;
        font-family:'Oswald', Helvetica;
        display: block;
        margin: 30px;
    }
    .push_button:before {
        background:#f0f0f0;
        background-image:-webkit-gradient(linear, 0% 0%, 0% 100%, from(#D0D0D0), to(#f0f0f0));

        -webkit-border-radius:5px;
        -moz-border-radius:5px;
        border-radius:5px;

        -webkit-box-shadow:0 1px 2px rgba(0, 0, 0, .5) inset, 0 1px 0 #FFF;
        -moz-box-shadow:0 1px 2px rgba(0, 0, 0, .5) inset, 0 1px 0 #FFF;
        box-shadow:0 1px 2px rgba(0, 0, 0, .5) inset, 0 1px 0 #FFF;

        position: absolute;
        content: "";
        left: -6px; right: -6px;
        top: -6px; bottom: -10px;
        z-index: -1;
    }

    .push_button:active {
        -webkit-box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset;
        top:5px;
    }
    .push_button:active:before{
        top: -11px;
        bottom: -5px;
        content: "";
    }

    .red {
        text-shadow:-1px -1px 0 #A84155;
        background: #D25068;
        border:1px solid #D25068;

        background-image:-webkit-linear-gradient(top, #F66C7B, #D25068);
        background-image:-moz-linear-gradient(top, #F66C7B, #D25068);
        background-image:-ms-linear-gradient(top, #F66C7B, #D25068);
        background-image:-o-linear-gradient(top, #F66C7B, #D25068);
        background-image:linear-gradient(to bottom, #F66C7B, #D25068);

        -webkit-border-radius:5px;
        -moz-border-radius:5px;
        border-radius:5px;

        -webkit-box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset, 0 4px 0 #AD4257, 0 4px 2px rgba(0, 0, 0, .5);
        -moz-box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset, 0 4px 0 #AD4257, 0 4px 2px rgba(0, 0, 0, .5);
        box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset, 0 4px 0 #AD4257, 0 4px 2px rgba(0, 0, 0, .5);
    }

    .red:hover {
        background: #F66C7B;
        background-image:-webkit-linear-gradient(top, #D25068, #F66C7B);
        background-image:-moz-linear-gradient(top, #D25068, #F66C7B);
        background-image:-ms-linear-gradient(top, #D25068, #F66C7B);
        background-image:-o-linear-gradient(top, #D25068, #F66C7B);
        background-image:linear-gradient(top, #D25068, #F66C7B);
    }

    .blue {
        text-shadow:-1px -1px 0 #2C7982;
        background: #3EACBA;
        border:1px solid #379AA4;
        background-image:-webkit-linear-gradient(top, #48C6D4, #3EACBA);
        background-image:-moz-linear-gradient(top, #48C6D4, #3EACBA);
        background-image:-ms-linear-gradient(top, #48C6D4, #3EACBA);
        background-image:-o-linear-gradient(top, #48C6D4, #3EACBA);
        background-image:linear-gradient(top, #48C6D4, #3EACBA);

        -webkit-border-radius:5px;
        -moz-border-radius:5px;
        border-radius:5px;

        -webkit-box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset, 0 4px 0 #338A94, 0 4px 2px rgba(0, 0, 0, .5);
        -moz-box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset, 0 4px 0 #338A94, 0 4px 2px rgba(0, 0, 0, .5);
        box-shadow:0 1px 0 rgba(255, 255, 255, .5) inset, 0 -1px 0 rgba(255, 255, 255, .1) inset, 0 4px 0 #338A94, 0 4px 2px rgba(0, 0, 0, .5);
    }

    .blue:hover {
        background: #48C6D4;
        background-image:-webkit-linear-gradient(top, #3EACBA, #48C6D4);
        background-image:-moz-linear-gradient(top, #3EACBA, #48C6D4);
        background-image:-ms-linear-gradient(top, #3EACBA, #48C6D4);
        background-image:-o-linear-gradient(top, #3EACBA, #48C6D4);
        background-image:linear-gradient(top, #3EACBA, #48C6D4);
    }

    .buttonUI {
        width: 130px;
        height: 40px;
        background: linear-gradient(to bottom, #4eb5e5 0%,#389ed5 100%); /* W3C */
        border: none;
        border-radius: 5px;
        position: relative;
        border-bottom: 4px solid #2b8bc6;
        color: #fbfbfb;
        font-weight: 600;
        font-family: 'Open Sans', sans-serif;
        text-shadow: 1px 1px 1px rgba(0,0,0,.4);
        font-size: 15px;
        text-align: left;
        text-indent: 5px;
        box-shadow: 0px 3px 0px 0px rgba(0,0,0,.2);
        cursor: pointer;


        /* for presentation */
        display: block;
        margin: 0 auto;
        margin-top: 30px;
    }
    .buttonUI:active {
        box-shadow: 0px 2px 0px 0px rgba(0,0,0,.2);
        top: 1px;
    }

    .buttonUI:after {
        content: "";
        width: 0;
        height: 0;
        display: block;
        border-top: 20px solid #187dbc;
        border-bottom: 20px solid #187dbc;
        border-left: 16px solid transparent;
        border-right: 20px solid #187dbc;
        position: absolute;
        opacity: 0.6;
        right: 0;
        top: 0;
        border-radius: 0 5px 5px 0;
    }


</style>
