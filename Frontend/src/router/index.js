import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import About from "../views/About.vue";
import ListAll from "../views/ListAll.vue";
import Detail from "../views/Details.vue"
import CreateEvent from "../views/CreateEvent.vue";
const history = createWebHistory('/sj2/');
const routes = [
  { path: "/", name: "Home", component: Home },
  { path: "/about", name: "About", component: About },
  { path: "/listevents", name: "ListEvent", component: ListAll },
  { path: "/detail", name: "Detail", component: Detail },
  { path: "/CreateEvent", name: "CreateEvent", component: CreateEvent },
];
const router = createRouter({ history, routes });
export default router;
