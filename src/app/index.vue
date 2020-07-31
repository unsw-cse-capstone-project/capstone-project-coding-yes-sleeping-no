<template>
  <div class="contour" style="width: 1280px;text-align: center;margin: 0 auto;">
    <el-row :gutter="20" style="margin: 0;padding: 10px;background-color: black;color: white;">
      <el-col :span="5" style="padding: 0 0 0 40px; text-align: left;">
        <span class="curPoi" style="font-size: 28px;vertical-align: middle;font-style: italic;">CYSN</span>
      </el-col>
      <el-col :span="15" style="padding: 0px;">
        <el-row :gutter="20" style="margin: 0px;">
          <el-col :span="16" style="padding: 0px;line-height: 40px;">
            <div class="flexNowarp" style="font-size: 14px;">
              <div class="flexItem curPoi">Live Concerts</div>
              <div class="flexItem curPoi">Movies</div>
              <div class="flexItem curPoi">Dramas</div>
              <div class="flexItem curPoi">Sport</div>
            </div>
          </el-col>
          <el-col :span="8" style="padding: 0;line-height: 40px;">
            <el-input v-model="keyword" placeholder="Search" class="input-with-select" @change="search(keyword)">
              <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="4" style="padding: 0px 20px 0px 0px;text-align: right;">
        <div class="curPoi" v-if="Object.keys(user).length === 0">
          <img src="../assets/img/login.png" alt="" width="40px;" style="vertical-align: middle; margin: 0 10px 0 0" @click="dialogVisible = true" />
          <span @click="dialogVisible = true">Sign In/Sign Up</span>
        </div>
        <div class="curPoi" v-if="Object.keys(user).length !== 0">
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link" style="color: white">
              <img v-if="imageUrl" :src="imageUrl" alt="" style="width: 40px; height: 40px; vertical-align: middle; margin: 0 10px 0 0; border-radius: 20px">
              <img v-else src="../assets/img/login.png" alt="" style="width: 40px; height: 40px; vertical-align: middle; margin: 0 10px 0 0; border-radius: 20px; border: 1px solid #DCDFE6">
              <span style="line-height: 40px;">{{user.user_name}}</span>
              <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="orderMgm">Order Management</el-dropdown-item>
              <el-dropdown-item command="accountSet">User Profile</el-dropdown-item>
              <el-dropdown-item v-if="user.status===1" disabled>Event Management</el-dropdown-item>
              <el-dropdown-item v-else-if="user.status===2" command="eventMgm">Event Management</el-dropdown-item>
              <el-dropdown-item divided command="logout">Logout</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-col>
    </el-row>
    <el-carousel height="700px" style="background-color: black">
      <el-carousel-item v-for="(item, index) in carouselList" :key="index">
        <img :src="item.cover_image" alt="" height="100%" />
      </el-carousel-item>
    </el-carousel>

    <div v-if="Object.keys(movies).length !== 0">
      <div style="background-color: white;padding: 80px 50px;">
        <div style="text-align: left;">
          <img src="../assets/img/type.png" alt="" width="40px;" />
          <span style="font-size: 22px;padding-left: 10px;font-style:italic">Movies</span>
        </div>
        <el-row style="height: 666px;">
          <el-col :span="14" style="height: 100%;border-radius: 15px;overflow: hidden;">
            <img :src="getPoster(this.movies, 0)" alt="" width="100%" height="100%" style="display: block;" />
          </el-col>
          <el-col v-if="Object.keys(movies).length > 1" :span="10" style="padding-left: 26px;">
            <div style="height: 320px;border-radius: 15px;margin-bottom: 26px;overflow: hidden;"><img :src="getPoster(this.movies, 1)" alt="" width="100%" height="100%" style="display: block;" /></div>
            <div v-if="Object.keys(movies).length > 2" style="height: 320px;border-radius: 15px;overflow: hidden;"><img :src="getPoster(this.movies, 2)" alt="" width="100%" height="100%" style="display: block;" /></div>
          </el-col>
        </el-row>
          <div v-for="(movie, index) in this.movies.slice(3)" class="flexItem" style="text-align: left; display: inline-block; margin-top: 20px">
            <img :src="getPoster(this.movies, index)" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">{{movie.title}}</div>
            <div style="font-size: 14px;color: #666;">{{movie.description}}</div>
            <div style="font-size: 14px;color: #666;">{{movie.start_date.split('T')[0]}} {{movie.start_time.split(/[T.]/)[1]}}</div>
            <div style="font-size: 20px;color: red;">${{movie.ticket_price}}</div>
          </div>
<!--        <div style="padding-top: 50px;">-->
<!--          <button style="background-color: #fff;border: 1px solid #e5e5e5;border-radius: 22px;padding: 15px 25px;cursor: pointer;">Load More</button>-->
<!--        </div>-->
      </div>
    </div>

<!--    <div v-if="concerts !== null">-->
<!--      <div style="background-color: white;padding: 80px 50px;">-->
<!--        <div style="text-align: left;">-->
<!--          <img src="../assets/img/type.png" alt="" width="40px;" />-->
<!--          <span style="font-size: 22px;padding-left: 10px;font-style:italic">Live Concerts</span>-->
<!--        </div>-->
<!--        <el-row style="height: 666px;">-->
<!--          <el-col :span="14" style="height: 100%;border-radius: 15px;overflow: hidden;">-->
<!--            <img :src="getPoster(this.concerts, 0)" alt="" width="100%" height="100%" style="display: block;" />-->
<!--          </el-col>-->
<!--          <el-col v-if="Object.keys(concerts).length > 1" :span="10" style="padding-left: 26px;">-->
<!--            <div style="height: 320px;border-radius: 15px;margin-bottom: 26px;overflow: hidden;"><img :src="getPoster(this.concerts, 1)" alt="" width="100%" height="100%" style="display: block;" /></div>-->
<!--            <div v-if="Object.keys(concerts).length > 2" style="height: 320px;border-radius: 15px;overflow: hidden;"><img :src="getPoster(this.concerts, 2)" alt="" width="100%" height="100%" style="display: block;" /></div>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <div v-for="(concert, index) in this.concerts.slice(3)" class="flexItem" style="text-align: left; display: inline-block; margin-top: 20px">-->
<!--          <img :src="getPoster(this.concerts, index)" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />-->
<!--          <div style="font-size: 20px;color: black;font-weight">{{concert.title}}</div>-->
<!--          <div style="font-size: 14px;color: #666;">{{concert.description}}</div>-->
<!--          <div style="font-size: 14px;color: #666;">{{concert.start_date.split('T')[0]}} {{concert.start_time.split(/[T.]/)[1]}}</div>-->
<!--          <div style="font-size: 20px;color: red;">${{concert.ticket_price}}</div>-->
<!--        </div>-->
<!--        &lt;!&ndash;        <div style="padding-top: 50px;">&ndash;&gt;-->
<!--        &lt;!&ndash;          <button style="background-color: #fff;border: 1px solid #e5e5e5;border-radius: 22px;padding: 15px 25px;cursor: pointer;">Load More</button>&ndash;&gt;-->
<!--        &lt;!&ndash;        </div>&ndash;&gt;-->
<!--      </div>-->
<!--    </div>-->

<!--    <div v-if="dramas !== null">-->
<!--      <div style="background-color: white;padding: 80px 50px;">-->
<!--        <div style="text-align: left;">-->
<!--          <img src="../assets/img/type.png" alt="" width="40px;" />-->
<!--          <span style="font-size: 22px;padding-left: 10px;font-style:italic">Dramas</span>-->
<!--        </div>-->
<!--        <el-row style="height: 666px;">-->
<!--          <el-col :span="14" style="height: 100%;border-radius: 15px;overflow: hidden;">-->
<!--            <img :src="getPoster(this.dramas, 0)" alt="" width="100%" height="100%" style="display: block;" />-->
<!--          </el-col>-->
<!--          <el-col v-if="Object.keys(dramas).length > 1" :span="10" style="padding-left: 26px;">-->
<!--            <div style="height: 320px;border-radius: 15px;margin-bottom: 26px;overflow: hidden;"><img :src="getPoster(this.dramas, 1)" alt="" width="100%" height="100%" style="display: block;" /></div>-->
<!--            <div v-if="Object.keys(dramas).length > 2" style="height: 320px;border-radius: 15px;overflow: hidden;"><img :src="getPoster(this.dramas, 2)" alt="" width="100%" height="100%" style="display: block;" /></div>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <div v-for="(drama, index) in this.dramas.slice(3)" class="flexItem" style="text-align: left; display: inline-block; margin-top: 20px">-->
<!--          <img :src="getPoster(this.dramas, index)" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />-->
<!--          <div style="font-size: 20px;color: black;font-weight">{{drama.title}}</div>-->
<!--          <div style="font-size: 14px;color: #666;">{{drama.description}}</div>-->
<!--          <div style="font-size: 14px;color: #666;">{{drama.start_date.split('T')[0]}} {{drama.start_time.split(/[T.]/)[1]}}</div>-->
<!--          <div style="font-size: 20px;color: red;">${{drama.ticket_price}}</div>-->
<!--        </div>-->
<!--        &lt;!&ndash;        <div style="padding-top: 50px;">&ndash;&gt;-->
<!--        &lt;!&ndash;          <button style="background-color: #fff;border: 1px solid #e5e5e5;border-radius: 22px;padding: 15px 25px;cursor: pointer;">Load More</button>&ndash;&gt;-->
<!--        &lt;!&ndash;        </div>&ndash;&gt;-->
<!--      </div>-->
<!--    </div>-->

<!--    <div v-if="sports !== null">-->
<!--      <div style="background-color: white;padding: 80px 50px;">-->
<!--        <div style="text-align: left;">-->
<!--          <img src="../assets/img/type.png" alt="" width="40px;" />-->
<!--          <span style="font-size: 22px;padding-left: 10px;font-style:italic">Sport</span>-->
<!--        </div>-->
<!--        <el-row style="height: 666px;">-->
<!--          <el-col :span="14" style="height: 100%;border-radius: 15px;overflow: hidden;">-->
<!--            <img :src="getPoster(this.sports, 0)" alt="" width="100%" height="100%" style="display: block;" />-->
<!--          </el-col>-->
<!--          <el-col v-if="Object.keys(sports).length > 1" :span="10" style="padding-left: 26px;">-->
<!--            <div style="height: 320px;border-radius: 15px;margin-bottom: 26px;overflow: hidden;"><img :src="getPoster(this.sports, 1)" alt="" width="100%" height="100%" style="display: block;" /></div>-->
<!--            <div v-if="Object.keys(sports).length > 2" style="height: 320px;border-radius: 15px;overflow: hidden;"><img :src="getPoster(this.sports, 2)" alt="" width="100%" height="100%" style="display: block;" /></div>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <div v-for="(sport, index) in this.sports.slice(3)" class="flexItem" style="text-align: left; display: inline-block; margin-top: 20px">-->
<!--          <img :src="getPoster(this.sports, index)" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />-->
<!--          <div style="font-size: 20px;color: black;font-weight">{{sport.title}}</div>-->
<!--          <div style="font-size: 14px;color: #666;">{{sport.description}}</div>-->
<!--          <div style="font-size: 14px;color: #666;">{{sport.start_date.split('T')[0]}} {{sport.start_time.split(/[T.]/)[1]}}</div>-->
<!--          <div style="font-size: 20px;color: red;">${{sport.ticket_price}}</div>-->
<!--        </div>-->
<!--        &lt;!&ndash;        <div style="padding-top: 50px;">&ndash;&gt;-->
<!--        &lt;!&ndash;          <button style="background-color: #fff;border: 1px solid #e5e5e5;border-radius: 22px;padding: 15px 25px;cursor: pointer;">Load More</button>&ndash;&gt;-->
<!--        &lt;!&ndash;        </div>&ndash;&gt;-->
<!--      </div>-->
<!--    </div>-->

    <div>
      <div style="background-color: white;padding: 80px 50px;">
        <div style="text-align: left;">
          <img src="../assets/img/type.png" alt="" width="40px;" />
          <span style="font-size: 22px;padding-left: 10px;font-style:italic">Live</span>
        </div>
        <el-row style="height: 666px;">
          <el-col :span="16" style="height: 100%;border-radius: 15px;overflow: hidden;">
            <img src="../assets/img/StarWars.png" alt="" width="100%" height="100%" style="display: block;" />
          </el-col>
          <el-col :span="8" style="padding-left: 26px;">
            <div style="height: 320px;border-radius: 15px;margin-bottom: 26px;overflow: hidden;"><img src="../assets/img/StarWars.png" alt="" width="100%" height="100%" style="display: block;" /></div>
            <div style="height: 320px;border-radius: 15px;overflow: hidden;"><img src="../assets/img/StarWars.png" alt="" width="100%" height="100%" style="display: block;" /></div>
          </el-col>
        </el-row>
        <div class="flexNowarp" style="margin-top: 30px;">
          <div class="flexItem" style="text-align: left;">
            <img src="../assets/img/joker.png" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">Joker</div>
            <div style="font-size: 14px;color: #666;">Introduction</div>
            <div style="font-size: 14px;color: #666;">4/6/2020 Saturday 18:30</div>
            <div style="font-size: 20px;color: red;">from $200</div>
          </div>
          <div class="flexItem" style="text-align: left;">
            <img src="../assets/img/joker.png" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">Joker</div>
            <div style="font-size: 14px;color: #666;">Introduction</div>
            <div style="font-size: 14px;color: #666;">4/6/2020 Saturday 18:30</div>
            <div style="font-size: 20px;color: red;">from $200</div>
          </div>
          <div class="flexItem" style="text-align: left;">
            <img src="../assets/img/joker.png" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">Joker</div>
            <div style="font-size: 14px;color: #666;">Introduction</div>
            <div style="font-size: 14px;color: #666;">4/6/2020 Saturday 18:30</div>
            <div style="font-size: 20px;color: red;">from $200</div>
          </div>
          <div class="flexItem" style="text-align: left;">
            <img src="../assets/img/joker.png" alt="" width="286px;" height="400px;" style="border-radius:15px;overflow: hidden;" />
            <div style="font-size: 20px;color: black;font-weight">Joker</div>
            <div style="font-size: 14px;color: #666;">Introduction</div>
            <div style="font-size: 14px;color: #666;">4/6/2020 Saturday 18:30</div>
            <div style="font-size: 20px;color: red;">from $200</div>
          </div>
        </div>
        <div style="padding-top: 50px;">
          <button style="background-color: #fff;border: 1px solid #e5e5e5;border-radius: 22px;padding: 15px 25px;cursor: pointer;">Load More</button>
        </div>
      </div>
    </div>
    <div style="margin-top: 50px;padding: 40px 50px;border-top: 1px solid #121212;">
      <el-row>
        <el-col :span="8" style="text-align: left;font-size: 16px;color: #121212;">
          <div class="flexNowarp" style="font-size: 14px;">
            <div class="flexItem curPoi">@CYSN copy @2020</div>
          </div>
        </el-col>
        <el-col :span="8" style="text-align: left;font-size: 16px;color: #121212;">
          <div class="flexNowarp" style="font-size: 14px;">
            <div class="flexItem curPoi">Help</div>
            <div class="flexItem curPoi">Contract</div>
            <div class="flexItem curPoi">About us</div>
          </div>
        </el-col>
        <el-col :span="8" style="text-align: right;">
          <img src="../assets/img/twitter.png" alt="" width="22px" height="20px" style="padding-right: 30px;" />
          <img src="../assets/img/ins.png" alt="" width="20px" height="20px" style="padding-right: 30px;" />
          <img src="../assets/img/facebook.png" alt="" width="20px" height="20px" />
        </el-col>
      </el-row>
    </div>

    <el-dialog :visible.sync="dialogVisible">

      <el-row style="height: 100%;">
        <el-col :span="11" style="height: 100%;">
          <img src="../assets/img/joker.png" alt="" width="100%" height="100%" />
        </el-col>
        <el-col :span="13">
          <div style="text-align: right; padding: 20px 20px 0px 0px;"><img @click="quit"  src="../assets/img/close.png" alt="" width="20px" height="20px" class="curPoi" /></div>
          <div v-if="showLogin">
            <el-row style="padding: 50px 40px;">
              <el-col :span="12" class="curDefault" style="font-size: 22px;font-weight: bold;text-align: left;">Sign In to CYSN</el-col>
              <el-col :span="12" class="curPoi" style="font-size: 14px;color: blue;text-align: right;" @click.native="showLogin=false"><span>Sign&nbsp;up</span></el-col><!-- @click="checkType" -->
            </el-row>
            <el-input placeholder="Email" v-model="user.email" style="margin: 0px 50px;width: 80%;" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/account.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <el-input placeholder="Password" v-model="user.password" style="margin: 20px 50px 0px;width: 80%;" type="password" :clearable="true">
              <template slot="prepend">
                <img src="../assets/img/pwd.png" alt="" width="18px" height="18px" />
              </template>
            </el-input>
            <button class="curPoi" @click="login" style="line-height: 44px;width: 80%;margin: 30px auto 0px;font-size: 15px;background-color: black;color: #fff;border-radius: 5px;border: none;">Sign&nbsp;in</button>
            <div class="curPoi" style="text-align: right;padding: 10px 50px 0px 0px;color: #4386F5;">
              <router-link to="/forgetPwd" style="text-decoration: none;">
                <span style="line-height: 40px;">Forget password?</span>
              </router-link>
            </div>
          </div>
          <div v-if="!showLogin">
            <el-row style="padding: 50px 40px;">
              <el-col :span="12" class="curDefault" style="font-size: 22px;font-weight: bold;text-align: left;">Sign Up to CYSN</el-col>
            </el-row>
            <el-input placeholder="Username" v-model="register.user_name" style="margin: 0 50px;width: 80%;" :clearable="true">
              <template slot="prepend">
<!--                <img src="../assets/img/account.png" alt="" width="18px" height="18px" />-->
                <i class="el-icon-user" style="font-size: 20px"></i>
              </template>
            </el-input>
            <el-input placeholder="Enter email" v-model="register.email" style="margin: 20px 50px 0;width: 80%;" :clearable="true">
              <template slot="prepend">
<!--                <img src="../assets/img/pwd.png"  />-->
                <i class="el-icon-message" style="font-size: 20px"></i>
              </template>
            </el-input>
            <el-input placeholder="Password" v-model="register.password" style="margin: 20px 50px 0;width: 80%;" type="password" :clearable="true">
              <template slot="prepend">
<!--                <img src="../assets/img/pwd.png" alt="" width="18px" height="18px" />-->
                <i class="el-icon-lock" style="font-size: 20px"></i>
              </template>
            </el-input>
            <el-input placeholder="Confirm Password" v-model="confirm_password" style="margin: 20px 50px 0;width: 80%;" type="password" :clearable="true">
              <template slot="prepend">
<!--                <img src="../assets/img/pwd.png" alt="" width="18px" height="18px" />-->
                <i class="el-icon-lock" style="font-size: 20px"></i>
              </template>
            </el-input>
            <div style="height: 40px; line-height: 40px; margin: 20px 50px 0;">
              <el-radio v-model="register.status" label="1">Customer</el-radio>
              <el-radio v-model="register.status" label="2">Host</el-radio>
            </div>
            <button class="curPoi" style="line-height: 44px;width: 80%;margin: 30px auto 0px;font-size: 15px;background-color: black;color: #fff;border-radius: 5px;border: none;" @click="signup">Create Account</button>
          </div>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // carouselList: [
      //   {img: require('../assets/img/ColdplayPara.png')},
      //   {img: require('../assets/img/FindMeSomeBodyToLove.png')},
      //   {img: require('../assets/img/Joker2.png')}
      // ],
      carouselList: [],
      dialogVisible: false,
      user: {},
      register: {
        status: '1'
      },
      showLogin: true,
      confirm_password: null,
      keyword: '',
      imageUrl: '',
      concerts: [],
      movies: [],
      dramas: [],
      sports: [],
    }
  },
  mounted() {},
  methods: {
    // login
    login() {
      // send request
      this.$http.post("/user/login", this.user).then(
              res=>{
                if(res.state){
                  this.user = res.user;
                  this.$message({
                    message: res.msg,
                    type: 'success'
                  });
                  localStorage.setItem("user", JSON.stringify(res));
                  this.dialogVisible = false;
                  if(this.user.avatar !== null){
                    let len = this.user.avatar.split('/').length - 1;
                    const file = this.user.avatar.split('/')[len];
                    this.imageUrl = require('/Users/edgar/Desktop/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/' + file);
                  }
                }
                else {
                  this.$message({
                    message: res.msg,
                    type: 'fail'
                  });
                  this.user = {}
                }
      })
    },
    signup() {
      if(this.register.email !== null && this.register.email !== '' && this.register.email !== undefined) {
        if(this.register.password !== null && this.register.password !== '' && this.register.password !== undefined){
          if(this.confirm_password !== null && this.confirm_password !== '' && this.confirm_password !== undefined){
            if(this.register.password === this.confirm_password) {
              this.$http.post("/user/register", this.register).then(
                      res=>{
                        if(res.state){
                          this.user = res.user;
                          this.$message({
                            message: res.msg,
                            type: 'success'
                          });
                          localStorage.setItem("user", JSON.stringify(res));
                          this.dialogVisible = false;
                        }
                        else {
                          this.$message({
                            message: res.msg,
                            type: 'fail'
                          })
                        }
                      }
              )

            } else {
              this.$message({
                message: 'Two passwords are different!',
                type: 'error'
              })
            }
          } else {
            this.$message({
              message: 'The second password is wrong!',
              type: 'error'
            })
          }
        } else {
          this.$message({
            message: 'The first password is wrong!',
            type: 'error'
          })
        }
      } else {
        this.$message({
          showClose: true,
          message: 'Email is wrong',
          type: 'error'
        })
      }
    },
    quit() {
      this.dialogVisible = false;
      this.showLogin = true;
    },
    handleCommand(command) {
      if(command !== 'logout') {
        location.href='#/'+command;
      }
      else {
        this.$http.get("/user/logout").then(
                res=>{
                  if(res.state){
                    this.$message({
                      message: res.msg,
                      type: 'success'
                    });
                    localStorage.removeItem("user");
                    this.user = {};
                    location.href = '/';
                  }
                  else {
                    this.$message({
                      message: res.msg,
                      type: 'fail'
                    });
                  }
                });
      }
    },
    search(keyword) {
      location.href='#/search/'+keyword;
    },
    getPoster(picList, index) {
      let pic = picList[index];
      if(pic !== undefined && pic.cover_image !== null){
        return this.getPic(pic.cover_image);
      } else {
        let path = "../assets/img/CYSNlogo.png";
        return path;
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
    }
    this.$http.get("/event/find/index").then(
            res=>{
              console.log(res);
              this.dramas = res.Drama;
              this.concerts = res.LiveConcerts;
              this.movies = res.Movies;
              this.sports = res.sports;
              this.carouselList = res.Recommended;
            })
  }
}
</script>

<style scope>
  .el-select .el-input {
    width: 100px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
  .el-dialog {
    border-radius: 25px;
    overflow: hidden;
    width: 900px;
    height: 600px;
  }

  .el-dialog__header {
    display: none;
  }

  .el-dialog__body {
    padding: 0px;
    height: 100%;
  }
  .rlink :visited {
    color: white;
  }
  .rlink {
    color: white;
  }
</style>
