import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import About from "../views/About.vue";
import ListAll from "../views/ListAll.vue";
import CreateEvent from "../views/CreateEvent.vue";
const history = createWebHistory();
const routes = [
  { path: "/Home", name: "Home", component: Home },
  { path: "/about", name: "About", component: About },
  { path: "/", name: "ListEvent", component: ListAll },
  { path: "/CreateEvent", name: "CreateEvent", component: CreateEvent },
];
const router = createRouter({ history, routes });
export default router;
