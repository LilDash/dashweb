import * as React from 'react';
import PropTypes from 'prop-types';
import './nav-grid.scss';

import { NavGridCell } from './NavGridCell';


class NavGrid extends React.Component {

	constructor(props) {
	    super(props);
	    //this.imageOnLoad = this.imageOnLoad.bind(this);
	    this.state = {
	      //isImageDisplayed: false,
	    };
	}



	navGridRow(cells) {
		return (
			<div className='nav-grid-row'>
				{ cells.map((y, j)=> <NavGridCell {...y} />) }
			</div>
		);
	}

	render() {

		
		return (
			<div className='nav-grid' >
				{ 
					this.props.navGrid.map((x, i) => 
						this.navGridRow(x)
					)
				}
			</div>
		);
	}
};

NavGrid.propTypes = {
  	navGrid: PropTypes.array.isRequired,
};

NavGrid.defaultProps = {
  	
};

export { NavGrid };