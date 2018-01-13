import React from 'react';


export default class ProcedureList extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    const procedureNames = this.props.list;
    console.log("render ProcedureList")
    console.table(this.props.list)
    return (
        <ul>
          {
            procedureNames.map((item, i) =>
                <li key={i}>
                  <button
                      onClick={this.props.callback}>{item}</button>
                </li>
            )
          }
        </ul>
    )
  }
}