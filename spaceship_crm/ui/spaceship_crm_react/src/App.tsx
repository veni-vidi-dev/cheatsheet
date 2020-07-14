/*
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
import React from 'react';
import './App.css';
import { BrowserRouter, Route, Switch } from 'react-router-dom'
import LoginPage from "./pages/LoginPage";
import ErrorPage from "./pages/ErrorPage";
import ActivitiesPage from "./pages/ActivitiesPage";
import AddUserPage from "./pages/AddUserPage";

function App() {
  return (
      <BrowserRouter>
        <Switch>
          <Route path="/login" exact={true} component={LoginPage} />
          <Route path="/error" exact={true} component={ErrorPage} />
          <Route path="/activities" exact={true} component={ActivitiesPage} />
          <Route path="/addusertoactivity" exact={true} component={AddUserPage} />
          <Route component={LoginPage} />
        </Switch>
      </BrowserRouter>
  );
}

export default App;
