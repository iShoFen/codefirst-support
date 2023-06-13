import {StyleSheet, TouchableOpacity, View, ViewStyle} from "react-native";
import {Comment} from "../../../model/issues/Comment";
import React from "react";
import {useColors} from "../../../themes/hooks/useColors";
import CSDivider from "../../commons/CSDivider";
import CSText from "../../commons/CSText";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";

type CommentItemProps = {
  comment: Comment
  style?: ViewStyle
}

export default function CommentItem(props: CommentItemProps) {

  const colors = useColors()

  const {
    comment,
    style
  } = props

  return (<View style={[styles.container, style, {
    backgroundColor: colors.tertiary
  }]}>
    <View style={styles.header}>
      <View style={styles.headerText}>
        <CSText text={comment.author} type="bold"/>
        <CSText text="-" type="small"/>
        <CSText text={comment.createdAt.toLocaleDateString()} type="small"/>
      </View>
      <TouchableOpacity style={{
        alignSelf: 'flex-end'
      }}>
        <MaterialCommunityIcons name="dots-horizontal" size={24} color={colors.text}/>
      </TouchableOpacity>
    </View>
    <CSDivider/>
    <CSText text={comment.content} type="small"/>
  </View>)
}

const styles = StyleSheet.create({
  container: {
    gap: 4,
    paddingVertical: 8,
    paddingHorizontal: 16,
    backgroundColor: 'white',
    borderRadius: 8
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  headerText: {
    flexDirection: 'row',
    alignItems: 'baseline',
    gap: 4
  }
})
