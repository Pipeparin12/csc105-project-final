import React from 'react';
import Navigation from './components/navigation';
import {BrowserRouter as Router, Switch, Route, Redirect} from 'react-router-dom';
import Home from './pages/home';
import Login from './pages/login';
import Register from './pages/register';
import CreateBlog from './pages/create-blog';
import EditBlog from './pages/edit-blog';

import Blog from './pages/blog';

function App() {
  const user = {
    firstName: 'Parin',
    lastName: 'Likhitworasak'
  }

  return (
    <Router>
      <div className="App">
        <Navigation user={user} />
        <Switch>
          <Route path="/blog/:id" component={Blog} />
          <Route path="/home" component={Home} />
          <Route path="/login" component={Login} />
          <Route path="/register" component={Register} />
          <Route path="/create-blog" component={CreateBlog} />
          <Route path="/edit-blog/:id" component={EditBlog} />
          <Route path="/" render={() => <Redirect to="/home" />} />
          <Route component={() => 404} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
