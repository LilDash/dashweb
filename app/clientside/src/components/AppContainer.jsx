import * as React from 'react';
import { Header } from './header/Header';

export default class AppContainer extends React.Component {
  render() {
    return (
    	<div className="app-container">
    		<Header />
    	</div>
    );
  }
}