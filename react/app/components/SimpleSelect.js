import React from 'react';

export default class SimpleSelect extends React.Component {
  constructor(props) {
    super(props);
    this.onSelect = this.onSelect.bind(this);
  }

  onSelect(e) {
    this.props.onSelect(e.target.value);
  }

  render() {
    const list = this.props.list;
    return (
        <select style={{/* display: none; */ height: "5.5rem"}}
                onChange={this.onSelect} multiple="true">
          {list.map((item, i) => <option key={i}>{item}</option>)}
        </select>
    )
  }
}