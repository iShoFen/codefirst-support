import {describe, expect, jest, test} from "@jest/globals";
import {configureStore} from "@reduxjs/toolkit";
import testIssueReducer from "../testIssueReducer";
import React from "react";
import {Provider} from "react-redux";
import {render, screen} from "@testing-library/react-native";
import IssueListItem from "../../../components/issues/IssueListItem";
import '@testing-library/jest-native/extend-expect'
import testAppReducer from "../../app/testAppReducer";

jest.useFakeTimers();

// Configure store for testing purpose
const store = configureStore({
  reducer: {
    issueReducer: testIssueReducer,
    appReducer: testAppReducer
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false
    })
});

//@ts-ignore
const Wrapper = ({children}) => (<Provider store={store}>{children}</Provider>)

describe('<IssueListItem/>', () => {
  test('Assert displayed values', () => {
    const expectedIssue = store.getState().issueReducer.issues[0]

    render(<Wrapper>
      <IssueListItem issue={expectedIssue} />
    </Wrapper>)

    expect(screen.getByTestId('issue-title')).toHaveTextContent(expectedIssue.title)
    expect(screen.getByTestId('issue-author')).toHaveTextContent(expectedIssue.author)
    expect(screen.getByTestId('issue-category-name')).toHaveTextContent(expectedIssue.category.name)
  })
})
