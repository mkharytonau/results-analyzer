#!/bin/zsh

while read bib; do
  echo "Downloading bib $bib..."
  curl -s "https://results.athlinks.com/individual?bib=$bib&eventId=1055375&eventCourseId=2380405" | jq -c -r '[.intervals[0].brackets[0].rank, .displayName, .bib, .intervals[0].pace.time.timeInMillis, .intervals[1].pace.time.timeInMillis, .intervals[2].pace.time.timeInMillis, .intervals[4].pace.time.timeInMillis, .intervals[6].pace.time.timeInMillis, .intervals[8].pace.time.timeInMillis] | @csv' | tr -d '"' >> $2
  echo "Bib $bib downloaded"
done <$1