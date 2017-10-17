import * as React from 'react';
import { Header } from './header/Header';
import './app-container.scss';

export default class AppContainer extends React.Component {
  render() {
    return (
    	<div className="app-container">
    		<Header />
    	</div>
    );
  }
}