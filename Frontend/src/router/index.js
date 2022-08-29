import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import About from "../views/About.vue";
import ListAll from "../views/ListAll.vue";
import Detail from "../views/Details.vue"
import CreateEvent from "../views/CreateEvent.vue";
import ListAllCategory from "../views/ListCategory.vue"
import CategoryDetail from "../views/CategoryDetail.vue"
import ListUsers from "../views/UserList.vue"
import UserDetail from "../views/UserDetail.vue"
import User from "../components/User.vue"
import SignUp from "../views/SignUp.vue"
import SignIn from "../views/SignIn.vue"

const history = createWebHistory('/sj2/');
const routes = [
  { path: "/", name: "Home", component: Home },
  { path: "/about", name: "About", component: About },
  { path: "/listevents", name: "ListEvent", component: ListAll },
  { path: "/detail/:id", name: "Detail", component: Detail },
  { path: "/CreateEvent", name: "CreateEvent", component: CreateEvent },
  {path: "/listcategory", name: "ListCategory", component: ListAllCategory },
  {path: "/categorydetail/:id", name: "CategoryDetail", component: CategoryDetail },
  {path: "/listusers", name: "ListUsers", component: ListUsers },
  {path: "/userdetail/:id", name: "UserDetail", component: UserDetail },
  {path: "/users", name: "Users", component: User},
  {path: "/signup", name: "SignUp", component: SignUp},
  {path: "/signin", name: "SignIn", component: SignIn},
  
  
];
const router = createRouter({ history, routes });
export default router;
